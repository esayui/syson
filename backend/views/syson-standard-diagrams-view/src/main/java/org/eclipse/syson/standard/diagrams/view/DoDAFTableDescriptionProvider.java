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
package org.eclipse.syson.standard.diagrams.view;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.sirius.components.view.RepresentationDescription;
import org.eclipse.sirius.components.view.builder.generated.table.TableBuilders;
import org.eclipse.sirius.components.view.builder.generated.view.ViewBuilders;
import org.eclipse.sirius.components.view.builder.providers.IColorProvider;
import org.eclipse.sirius.components.view.builder.providers.IRepresentationDescriptionProvider;
import org.eclipse.sirius.components.view.table.CellDescription;
import org.eclipse.sirius.components.view.table.ColumnDescription;
import org.eclipse.syson.sysml.SysmlPackage;
import org.eclipse.syson.util.AQLConstants;
import org.eclipse.syson.util.SysMLMetamodelHelper;

/**
 * Table description for DoDAF Table views (StdV-1, StdV-2, AV-2).
 * Displays a multi-column table with category, name and description.
 *
 * @author dousheng
 */
public class DoDAFTableDescriptionProvider implements IRepresentationDescriptionProvider {

    public static final String DESCRIPTION_NAME = "DoDAF Table View";

    private final TableBuilders tableBuilders = new TableBuilders();
    private final ViewBuilders viewBuilders = new ViewBuilders();

    @Override
    public RepresentationDescription create(IColorProvider colorProvider) {
        String domainType = SysMLMetamodelHelper.buildQualifiedName(SysmlPackage.eINSTANCE.getNamespace());

        var rowDescription = this.tableBuilders.newRowDescription()
                .name("DoDAFTable-Row")
                .semanticCandidatesExpression("aql:self.getTableElements()->toPaginatedData(cursor,direction,size)")
                .depthLevelExpression("0")
                .headerLabelExpression("条目")
                .initialHeightExpression("-1")
                .isResizableExpression(AQLConstants.AQL_FALSE)
                .build();

        return this.tableBuilders.newTableDescription()
                .name(DESCRIPTION_NAME)
                .titleExpression("aql:self.name")
                .domainType(domainType)
                .columnDescriptions(this.createColumns().toArray(ColumnDescription[]::new))
                .cellDescriptions(this.createCells().toArray(CellDescription[]::new))
                .rowDescription(rowDescription)
                .pageSizeOptionsExpression("aql:Sequence{10,20,50}")
                .useStripedRowsExpression("aql:true")
                .build();
    }

    private List<ColumnDescription> createColumns() {
        List<ColumnDescription> columns = new ArrayList<>();

        columns.add(this.tableBuilders.newColumnDescription()
                .name("DoDAFTable-Col-Category")
                .semanticCandidatesExpression("aql:'Category'")
                .headerLabelExpression("分类")
                .initialWidthExpression("150")
                .isResizableExpression(AQLConstants.AQL_TRUE)
                .isSortableExpression(AQLConstants.AQL_TRUE)
                .build());

        columns.add(this.tableBuilders.newColumnDescription()
                .name("DoDAFTable-Col-Name")
                .semanticCandidatesExpression("aql:'Name'")
                .headerLabelExpression("名称")
                .initialWidthExpression("250")
                .isResizableExpression(AQLConstants.AQL_TRUE)
                .isSortableExpression(AQLConstants.AQL_TRUE)
                .build());

        columns.add(this.tableBuilders.newColumnDescription()
                .name("DoDAFTable-Col-Description")
                .semanticCandidatesExpression("aql:'Description'")
                .headerLabelExpression("描述")
                .initialWidthExpression("400")
                .isResizableExpression(AQLConstants.AQL_TRUE)
                .isSortableExpression(AQLConstants.AQL_TRUE)
                .build());

        return columns;
    }

    private List<CellDescription> createCells() {
        List<CellDescription> cells = new ArrayList<>();

        cells.add(this.tableBuilders.newCellDescription()
                .name("DoDAFTable-Cell-Category")
                .preconditionExpression("aql:columnTargetObject == 'Category'")
                .valueExpression("aql:self.eContainer().name")
                .cellWidgetDescription(this.tableBuilders.newCellTextfieldWidgetDescription()
                        .build())
                .build());

        cells.add(this.tableBuilders.newCellDescription()
                .name("DoDAFTable-Cell-Name")
                .preconditionExpression("aql:columnTargetObject == 'Name'")
                .valueExpression("aql:self.declaredName")
                .cellWidgetDescription(this.tableBuilders.newCellTextfieldWidgetDescription()
                        .body(this.viewBuilders.newSetValue()
                                .featureName("declaredName")
                                .valueExpression("aql:newValue")
                                .build())
                        .build())
                .build());

        cells.add(this.tableBuilders.newCellDescription()
                .name("DoDAFTable-Cell-Description")
                .preconditionExpression("aql:columnTargetObject == 'Description'")
                .valueExpression("aql:self.getDocumentationBody()")
                .cellWidgetDescription(this.tableBuilders.newCellTextareaWidgetDescription()
                        .build())
                .build());

        return cells;
    }
}
