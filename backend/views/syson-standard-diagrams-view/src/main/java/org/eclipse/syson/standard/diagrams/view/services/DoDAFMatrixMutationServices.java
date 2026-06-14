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

import org.eclipse.syson.sysml.Documentation;
import org.eclipse.syson.sysml.Element;
import org.eclipse.syson.sysml.SysmlFactory;

/**
 * Mutation services for DoDAF Matrix View table.
 */
public class DoDAFMatrixMutationServices {

    /**
     * Returns the body of the first Documentation owned by the element, or empty string.
     */
    public String getDocumentationBody(Element element) {
        return element.getDocumentation().stream()
                .map(Documentation::getBody)
                .findFirst()
                .orElse("");
    }

    /**
     * Sets or updates the documentation body for the element.
     * Creates a new Documentation if none exists.
     */
    public boolean editDocumentation(Element element, String newValue) {
        var documentation = element.getDocumentation().stream()
                .findFirst()
                .orElse(null);
        if (documentation != null) {
            documentation.setBody(newValue);
        } else if (newValue != null && !newValue.isEmpty()) {
            var newDoc = SysmlFactory.eINSTANCE.createDocumentation();
            newDoc.setBody(newValue);
            var owningMembership = SysmlFactory.eINSTANCE.createOwningMembership();
            element.getOwnedRelationship().add(owningMembership);
            owningMembership.getOwnedRelatedElement().add(newDoc);
        }
        return true;
    }
}
