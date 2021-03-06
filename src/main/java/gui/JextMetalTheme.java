package com.pca.gui;

/*
 * 11:46:35 06/06/00
 *
 * JextMetalTheme.java - Un nouveau thème pour le L&F Metal
 * Copyright (C) 2000 Romain Guy
 * guy.romain@bigfoot.com
 * www.jext.org
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */


import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;
import java.awt.*;

/**
 * Un nouveau thème Metal.
 */

public class JextMetalTheme extends DefaultMetalTheme
{
    // cette fonte sera utilisée pour TOUS nos composants
    private FontUIResource font = new FontUIResource(new Font("Dialog", Font.PLAIN, 11));

    public JextMetalTheme()
    {
        super();
    }

    // font des menus
    public FontUIResource getMenuTextFont()
    {
        return font;
    }

    // fonte système
    public FontUIResource getSystemTextFont()
    {
        return font;
    }

    // autre fonte
    public FontUIResource getUserTextFont()
    {
        return font;
    }

    // fonte de la fenêtre interne
    public FontUIResource getWindowTitleFont()
    {
        return font;
    }
}

// End of JextSeparatorUI.java
