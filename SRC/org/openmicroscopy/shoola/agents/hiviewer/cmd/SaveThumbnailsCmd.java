/*
 * org.openmicroscopy.shoola.agents.hiviewer.cmd.SaveThumbnailsCmd
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

package org.openmicroscopy.shoola.agents.hiviewer.cmd;

//Java imports

//Third-party libraries

//Application-internal dependencies
import org.openmicroscopy.shoola.agents.hiviewer.browser.Browser;
import org.openmicroscopy.shoola.agents.hiviewer.browser.ImageDisplay;
import org.openmicroscopy.shoola.agents.hiviewer.saver.ContainerSaver;
import org.openmicroscopy.shoola.agents.hiviewer.view.HiViewer;
import org.openmicroscopy.shoola.util.ui.UIUtilities;

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
public class SaveThumbnailsCmd
    implements ActionCmd
{

    /** Reference to the model. */
    private HiViewer                model;
    
    
    /**
     * Creates a new command to save the thumbnails contained in a 
     * Dataset/Category.
     * If the node is not a node containing thumbnails, no action is taken.
     * 
     * @param model The Model which has a reference to the browser.
     *              Mustn't be <code>null</code>.
     */
    public SaveThumbnailsCmd(HiViewer model) 
    { 
        if (model == null) throw new NullPointerException("No model");
        this.model = model;
    }
    
    /** Implemented as specified by {@link ActionCmd}. */
    public void execute()
    {
        Browser browser = model.getBrowser();
        ImageDisplay selectedDisplay = browser.getSelectedDisplay();
        if (selectedDisplay.containsImages()) {
            SaveThumbnailsVisitor visitor = new SaveThumbnailsVisitor(model);
            selectedDisplay.accept(visitor);
            //Now create the FileChooser
            ContainerSaver saver = new ContainerSaver(model.getUI(), 
                    visitor.getThumbnails());
            saver.pack();
            UIUtilities.centerAndShow(saver);
        }
    }

}
