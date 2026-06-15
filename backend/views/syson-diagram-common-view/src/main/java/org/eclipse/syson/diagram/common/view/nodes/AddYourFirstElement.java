/*******************************************************************************
 * Copyright (c) 2026 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.syson.diagram.common.view.nodes;

/**
 * Base64 version of the add_your_first_element image.
 *
 * @author arichard
 */
public class AddYourFirstElement {

    
    private static String BASE64;
    
    static {{
        try (var in = AddYourFirstElement.class.getResourceAsStream("/images/add_your_first_element.svg")) {{
            if (in != null) {{
                var bytes = in.readAllBytes();
                BASE64 = "data:image/svg+xml;base64," + java.util.Base64.getEncoder().encodeToString(bytes);
            }}
        }} catch (java.io.IOException e) {{
            BASE64 = "";
        }}
    }}
    
    public static String getBase64() {{
        return BASE64;
    }}

    public static final String COLOR_NAME = "AddYourFirstElement";
}
