/*
 * org.openmicroscopy.shoola.agents.viewer.ViewerCtrl
 *
 *------------------------------------------------------------------------------
 *
 *  Copyright (C) 2004 Open Microscopy Environment
 *      Massachusetts Institute of Technology,
 *      National Institutes of Health,
 *      University of Dundee
 *
 *
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation; either
 *    version 2.1 of the License, or (at your option) any later version.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 *
 *    You should have received a copy of the GNU Lesser General Public
 *    License along with this library; if not, write to the Free Software
 *    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *------------------------------------------------------------------------------
 */

package org.openmicroscopy.shoola.agents.viewer;

//Java imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuItem;

//Third-party libraries

//Application-internal dependencies
import org.openmicroscopy.shoola.agents.viewer.controls.NavigationPalette;
import org.openmicroscopy.shoola.agents.viewer.util.ImageSaver;
import org.openmicroscopy.shoola.env.config.Registry;
import org.openmicroscopy.shoola.env.rnd.metadata.PixelsDimensions;

/** 
 * 
 *
 * @author  Jean-Marie Burel &nbsp;&nbsp;&nbsp;&nbsp;
 * 				<a href="mailto:j.burel@dundee.ac.uk">j.burel@dundee.ac.uk</a>
 * @author  <br>Andrea Falconi &nbsp;&nbsp;&nbsp;&nbsp;
 * 				<a href="mailto:a.falconi@dundee.ac.uk">
 * 					a.falconi@dundee.ac.uk</a>
 * @version 2.2 
 * <small>
 * (<b>Internal version:</b> $Revision$ $Date$)
 * </small>
 * @since OME2.2
 */
public class ViewerCtrl
	implements ActionListener
{
	static final int			V_VISIBLE = 0;
	static final int			CONTROL = 1;
	static final int			SAVE_AS = 2;
	
	private NavigationPalette	palette;
	private Viewer				abstraction;
	
	ViewerCtrl(Viewer abstraction)
	{
		this.abstraction = abstraction;
	}
	
	/** Forward event to {@link Viewer}. */
	public JFrame getReferenceFrame()
	{
		return abstraction.getRegistry().getTopFrame().getFrame();
	}
	
	/** Forward event to {@link Viewer}. */
	public Registry getRegistry()
	{
		return abstraction.getRegistry();
	}
	
	/** Return the buffered Image displayed. */
	public BufferedImage getBufferedImage()
	{
		return null;
	}
	
	/** Forward event to {@link Viewer}. */
	public PixelsDimensions getPixelsDims()
	{
		return abstraction.getPixelsDims();
	}
	
	/** Forward event to {@link Viewer}. */
	public int getDefaultT()
	{
		return abstraction.getDefaultT();
	}
	
	/** Forward event to {@link Viewer}. */
	public int getDefaultZ()
	{
		return abstraction.getDefaultZ();
	}
	
	public void onPlaneSelected(int z, int t)
	{
		abstraction.onPlaneSelected(z, t);
	}
	
	/** 
	 * Returns the abstraction component of this agent.
	 *
	 * @return  See above.
	 */
	Viewer getAbstraction()
	{
		return abstraction;
	}
	
	/** Attach listener to a menu Item. */
	void setMenuItemListener(JMenuItem item, int id)
	{
		item.setActionCommand(""+id);
		item.addActionListener(this);
	}
	
	/** Handles events. */
	public void actionPerformed(ActionEvent e) 
	{
		String s = (String) e.getActionCommand();
		try {
		   int index = Integer.parseInt(s);
		   switch (index) { 
				case V_VISIBLE:
					abstraction.activate();
					break;
				case CONTROL:
					showControls();
					break; 	
				case SAVE_AS:
					new ImageSaver(this);
					break;
		   }
		} catch(NumberFormatException nfe) {   
			   throw nfe;  //just to be on the safe side...
		} 
	}
	
	/** Forward event to {@link RenderingAgtUIF}. */
	public void showDialog(JDialog dialog)
	{
		abstraction.getPresentation().showDialog(dialog);
	}
	
	private void showControls()
	{
		if (palette == null) palette = new NavigationPalette(this);
		showDialog(palette);		
	}
	
	
}
