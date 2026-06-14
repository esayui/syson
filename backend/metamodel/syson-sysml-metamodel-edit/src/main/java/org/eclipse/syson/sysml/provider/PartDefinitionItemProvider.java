/**
 * Copyright (c) 2023, 2024 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 */
package org.eclipse.syson.sysml.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.syson.sysml.PartDefinition;

/**
 * This is the item provider adapter for a {@link org.eclipse.syson.sysml.PartDefinition} object. <!-- begin-user-doc
 * --> <!-- end-user-doc -->
 *
 * @generated
 */
public class PartDefinitionItemProvider extends ItemDefinitionItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public PartDefinitionItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * This returns the property descriptors for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
        if (this.itemPropertyDescriptors == null) {
            super.getPropertyDescriptors(object);

        }
        return this.itemPropertyDescriptors;
    }

    /**
     * This returns PartDefinition.svg. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public Object getImage(Object object) {
        String dodafIcon = this.getDodafIconPath(object);
        if (dodafIcon != null) {
            return this.overlayImage(object, this.getResourceLocator().getImage(dodafIcon));
        }
        return this.overlayImage(object, this.getResourceLocator().getImage("full/obj16/PartDefinition.svg"));
    }

    private String getDodafIconPath(Object object) {
        if (object instanceof org.eclipse.syson.sysml.Element element) {
            if (element.getAliasIds().contains("dodaf:capability")) return "full/obj16/DodafCapability.svg";
            if (element.getAliasIds().contains("dodaf:operational")) return "full/obj16/DodafOperationalNode.svg";
            if (element.getAliasIds().contains("dodaf:system")) return "full/obj16/DodafSystemNode.svg";
            if (element.getAliasIds().contains("dodaf:organization")) return "full/obj16/DodafOrganization.svg";
            if (element.getAliasIds().contains("dodaf:exchange")) return "full/obj16/DodafInformationExchange.svg";
        }
        return null;
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getText(Object object) {
        String label = ((PartDefinition) object).getName();
        return label == null || label.length() == 0 ? this.getString("_UI_PartDefinition_type") : this.getString("_UI_PartDefinition_type") + " " + label;
    }

    /**
     * This handles model notifications by calling {@link #updateChildren} to update any cached children and by creating
     * a viewer notification, which it passes to {@link #fireNotifyChanged}. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    @Override
    public void notifyChanged(Notification notification) {
        this.updateChildren(notification);
        super.notifyChanged(notification);
    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children that can be created
     * under this object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);
    }

}
