package org.eclipse.syson.sysmlcustomnodes;

import org.eclipse.sirius.components.view.UserColor;
import org.eclipse.sirius.components.view.diagram.NodeStyleDescription;

public interface DodafOrganizationStyleDescription extends NodeStyleDescription {
    UserColor getBackground();
    void setBackground(UserColor value);
}
