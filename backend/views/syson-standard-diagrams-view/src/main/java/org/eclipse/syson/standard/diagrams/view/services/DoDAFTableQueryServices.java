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
package org.eclipse.syson.standard.diagrams.view.services;

import java.util.List;

import org.eclipse.syson.sysml.Element;
import org.eclipse.syson.sysml.ViewUsage;

/**
 * Query services for the DoDAF Table View.
 */
public class DoDAFTableQueryServices {

    /**
     * Returns all elements owned by the ViewUsage.
     */
    public List<Element> getTableElements(ViewUsage viewUsage) {
        return viewUsage.getOwnedElement().stream().toList();
    }
}
