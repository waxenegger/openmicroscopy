/*
 * org.openmicroscopy.shoola.util.filter.file.DVFilter
 *
 *------------------------------------------------------------------------------
 *  Copyright (C) 2006 University of Dundee. All rights reserved.
 *
 *
 * 	This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 *------------------------------------------------------------------------------
 */

package org.openmicroscopy.shoola.util.filter.file;

//Java imports
import java.io.File;
import javax.swing.filechooser.FileFilter;

//Third-party libraries

//Application-internal dependencies

/** 
 * 
 * Filters the <code>DV</code> files.
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
public class DVFilter
	extends CustomizedFileFilter
{
	
	/** File extension. */
	public static final String 		DV = "dv";
	
	/** File extension. */
	public static final String 		R3D = "r3d";
	
	/** File extension. */
	public static final String 		R3D_D3D = "r3d_d3d";
	
	/** The possible extensions. */
	public static final String[] 	extensions;
	
	/** The description of the filter. */
	private static final String		description;
	
	static {
		extensions = new String[3];
		extensions[0] = DV;
		extensions[1] = R3D;
		extensions[2] = R3D_D3D;
		String s = "Deltavision (";
		for (int i = 0; i < extensions.length; i++) {
			s += "*."+extensions[i];
			if (i < extensions.length-1)
				s += ", ";
		}
		s += ")";
		description = s;
	}
	
	/**
	 * 	Overridden to return the extension of the filter.
	 * 	@see CustomizedFileFilter#getExtension()
	 */
	public String getExtension() { return DV; }
	
    /**
     * Overridden to return the description of the filter.
     * @see FileFilter#getDescription()
     */
	public String getDescription() { return description; }
		
    /**
     * Overridden to accept file with the declared file extensions.
     * @see FileFilter#accept(File)
     */
	public boolean accept(File f)
	{
		if (f == null) return false;
		if (f.isDirectory()) return true;
		return isSupported(f.getName(), extensions);
	}
	
	/**
	 * Overridden to accept the file identified by its name.
	 * @see CustomizedFileFilter#accept(String)
	 */
	public boolean accept(String fileName)
	{
		return isSupported(fileName, extensions);
	}
	
}
