# feature/chinese-localization vs main 差异文件清单

> 生成日期: 2026-06-15 | 分支: feature/chinese-localization → main

## 概览

| 类型 | 数量 |
|------|------|
| 修改 (M) | 255 |
| 新增 (A) | 174 |
| **合计** | 429 |

---

## backend/application/syson-application-configuration

| 状态 | 文件 | 说明 |
|------|------|------|
| 🔧 | `pom.xml` | Maven 依赖配置 |
## backend/application/syson-application-configuration/src/main

| 状态 | 文件 | 说明 |
|------|------|------|
| 🔧 | `SysMLv2PropertiesConfigurer.java` |  |
| ➕ | `DodafOperationalNodeStyle.java` |  |
| ➕ | `DodafOperationalNodeStyleDeserializer.java` |  |
| ➕ | `DodafOperationalNodeStyleProvider.java` |  |
| ➕ | `DodafOperationalNodeAppearanceInput.java` |  |
| ➕ | `EditDodafOperationalNodeAppearanceInput.java` |  |
| ➕ | `MutationEditDodafOperationalNodeAppearanceDataFetcher.java` |  |
| ➕ | `DodafOperationalNodeAppearanceHandler.java` |  |
| ➕ | `EditDodafOperationalNodeAppearanceEventHandler.java` |  |
| 🔧 | `EStructuralFeatureLabelProvider.java` |  |
| ➕ | `Dodafv2JsonExposeInjector.java` |  |
| ➕ | `Dodafv2TemplateBuilder.java` | DoDAF v2.0 模板构建器（8 视角 52 模型） |
| 🔧 | `SysMLv2ProjectTemplatesProvider.java` | 项目模板提供者（dodafv2.png） |
| 🔧 | `SysMLv2SemanticDataTemplatesInitializer.java` | 语义数据模板加载器 |
| 🔧 | `SysMLv2TemplatesRepresentationInitializer.java` | 模板表示初始化器（自动创建图表/表格/甘特图） |
| 🔧 | `SysONDefaultResourceProvider.java` |  |
| 🔧 | `IDefaultSysMLv2ResourceProvider.java` |  |
| ➕ | `Capability.svg` | DoDAF Capability 图标 |
| ➕ | `InformationExchange.svg` | DoDAF Exchange 图标 |
| ➕ | `OperationalNode.svg` | DoDAF Operational 图标 |
| ➕ | `Organization.svg` | DoDAF Organization 图标 |
| ➕ | `SystemNode.svg` | DoDAF System 图标 |
| ➕ | `dodafv2.png` | DoDAF v2 模板图片 |
| 🔧 | `import.svg` |  |
| 🔧 | `publish.svg` |  |
| 🔧 | `sysmlcustomnodes.graphqls` | 自定义节点 GraphQL 模式 |
## backend/application/syson-application/src/main

| 状态 | 文件 | 说明 |
|------|------|------|
| 🔧 | `SysONApplication.java` |  |
| ➕ | `SysONLocaleConfiguration.java` | 中文语言配置 |
| 🔧 | `application.properties` | 应用配置 |
| ➕ | `sirius-components-core.json` |  |
| ➕ | `sirius-components-deck.json` |  |
| ➕ | `sirius-components-diagrams.json` |  |
| ➕ | `sirius-components-formdescriptioneditors.json` |  |
| ➕ | `sirius-components-forms.json` |  |
| ➕ | `sirius-components-gantt.json` |  |
| ➕ | `sirius-components-palette.json` |  |
| ➕ | `sirius-components-portals.json` |  |
| ➕ | `sirius-components-selection.json` |  |
| ➕ | `sirius-components-tables.json` |  |
| ➕ | `sirius-components-trees.json` |  |
| ➕ | `sirius-components-validation.json` |  |
| ➕ | `sirius-components-widget-reference.json` |  |
| ➕ | `sirius-web-application.json` |  |
| ➕ | `sirius-components-core.json` |  |
| ➕ | `sirius-components-deck.json` |  |
| ➕ | `sirius-components-diagrams.json` |  |
| ➕ | `sirius-components-formdescriptioneditors.json` |  |
| ➕ | `sirius-components-forms.json` |  |
| ➕ | `sirius-components-gantt.json` |  |
| ➕ | `sirius-components-palette.json` |  |
| ➕ | `sirius-components-portals.json` |  |
| ➕ | `sirius-components-selection.json` |  |
| ➕ | `sirius-components-tables.json` |  |
| ➕ | `sirius-components-trees.json` |  |
| ➕ | `sirius-components-validation.json` |  |
| ➕ | `sirius-components-widget-reference.json` |  |
| ➕ | `sirius-web-application.json` |  |
## backend/metamodel/syson-siriusweb-customnodes-metamodel/src/main

| 状态 | 文件 | 说明 |
|------|------|------|
| ➕ | `DodafCapabilityStyleDescription.java` |  |
| ➕ | `DodafInformationExchangeStyleDescription.java` |  |
| ➕ | `DodafOperationalNodeStyleDescription.java` |  |
| ➕ | `DodafOrganizationStyleDescription.java` |  |
| ➕ | `DodafSystemNodeStyleDescription.java` |  |
| 🔧 | `SysMLCustomnodesFactory.java` |  |
| ➕ | `DodafCapabilityStyleDescriptionImpl.java` |  |
| ➕ | `DodafInformationExchangeStyleDescriptionImpl.java` |  |
| ➕ | `DodafOperationalNodeStyleDescriptionImpl.java` |  |
| ➕ | `DodafOrganizationStyleDescriptionImpl.java` |  |
| ➕ | `DodafSystemNodeStyleDescriptionImpl.java` |  |
| 🔧 | `SysMLCustomnodesFactoryImpl.java` |  |
| 🔧 | `sysml-customnodes.ecore` | 自定义节点 EMF 模型 |
## backend/metamodel/syson-sysml-metamodel-edit/src/main

| 状态 | 文件 | 说明 |
|------|------|------|
| 🔧 | `ActionUsageItemProvider.java` | ActionUsage 资源管理器项提供者（DoDAF 图标） |
| 🔧 | `PartDefinitionItemProvider.java` | PartDefinition 资源管理器项提供者（DoDAF 图标） |
| 🔧 | `PartUsageItemProvider.java` | PartUsage 资源管理器项提供者（DoDAF 图标） |
| 🔧 | `AcceptActionUsage.svg` |  |
| 🔧 | `ActionDefinition.svg` |  |
| 🔧 | `ActionUsage.svg` |  |
| 🔧 | `Actor.svg` |  |
| 🔧 | `ActorMembership.svg` |  |
| 🔧 | `AllocationDefinition.svg` |  |
| 🔧 | `AllocationUsage.svg` |  |
| 🔧 | `AnalysisCaseDefinition.svg` |  |
| 🔧 | `AnalysisCaseUsage.svg` |  |
| 🔧 | `Annotation.svg` |  |
| 🔧 | `AssertConstraintUsage.svg` |  |
| 🔧 | `AssignmentActionUsage.svg` |  |
| 🔧 | `Association.svg` |  |
| 🔧 | `AssociationStructure.svg` |  |
| 🔧 | `AttributeDefinition.svg` |  |
| 🔧 | `Behavior.svg` |  |
| 🔧 | `BindingConnector.svg` |  |
| 🔧 | `BindingConnectorAsUsage.svg` |  |
| 🔧 | `BooleanExpression.svg` |  |
| 🔧 | `CalculationDefinition.svg` |  |
| 🔧 | `CaseUsage.svg` |  |
| 🔧 | `Class.svg` |  |
| 🔧 | `Classifier.svg` |  |
| 🔧 | `CollectExpression.svg` |  |
| 🔧 | `ConcernDefinition.svg` |  |
| 🔧 | `ConcernUsage.svg` |  |
| 🔧 | `ConjugatedPortDefinition.svg` |  |
| 🔧 | `ConjugatedPortTyping.svg` |  |
| 🔧 | `Conjugation.svg` |  |
| 🔧 | `ConnectionDefinition.svg` |  |
| 🔧 | `ConnectionUsage.svg` |  |
| 🔧 | `Connector.svg` |  |
| 🔧 | `ConnectorDefinition.svg` |  |
| 🔧 | `ConnectorUsage.svg` |  |
| 🔧 | `ConstraintDefinition.svg` |  |
| 🔧 | `ConstraintUsage.svg` |  |
| 🔧 | `ConstructorExpression.svg` |  |
| 🔧 | `CrossSubsetting.svg` |  |
| 🔧 | `DataType.svg` |  |
| 🔧 | `DecisionNode.svg` |  |
| 🔧 | `Definition.svg` |  |
| 🔧 | `Dependency.svg` |  |
| 🔧 | `Differencing.svg` |  |
| 🔧 | `Disjoining.svg` |  |
| 🔧 | `Documentation.svg` |  |
| ➕ | `DodafCapability.svg` | DoDAF Capability 资源管理器图标 |
| ➕ | `DodafInformationExchange.svg` | DoDAF Exchange 资源管理器图标 |
| ➕ | `DodafOperationalNode.svg` | DoDAF Operational 资源管理器图标 |
| ➕ | `DodafOrganization.svg` | DoDAF Organization 资源管理器图标 |
| ➕ | `DodafSystemNode.svg` | DoDAF System 资源管理器图标 |
| 🔧 | `ElementFilterMembership.svg` |  |
| 🔧 | `EndFeatureMembership.svg` |  |
| 🔧 | `EnumerationDefinition.svg` |  |
| 🔧 | `EnumerationUsage.svg` |  |
| 🔧 | `EventOccurrenceUsage.svg` |  |
| 🔧 | `ExhibitStateUsage.svg` |  |
| 🔧 | `Expression.svg` |  |
| 🔧 | `Feature.svg` |  |
| 🔧 | `FeatureChainExpression.svg` |  |
| 🔧 | `FeatureChaining.svg` |  |
| 🔧 | `FeatureInverting.svg` |  |
| 🔧 | `FeatureMembership.svg` |  |
| 🔧 | `FeatureReferenceExpression.svg` |  |
| 🔧 | `FeatureTyping.svg` |  |
| 🔧 | `FeatureValue.svg` |  |
| 🔧 | `Flow.svg` |  |
| 🔧 | `FlowDefinition.svg` |  |
| 🔧 | `FlowEnd.svg` |  |
| 🔧 | `FlowUsage.svg` |  |
| 🔧 | `ForLoopActionUsage.svg` |  |
| 🔧 | `ForkNode.svg` |  |
| 🔧 | `FramedConcernMembership.svg` |  |
| 🔧 | `Function.svg` |  |
| 🔧 | `IfActionUsage.svg` |  |
| 🔧 | `Import.svg` |  |
| 🔧 | `IncludeUseCaseUsage.svg` |  |
| 🔧 | `IndexExpression.svg` |  |
| 🔧 | `InstantiationExpression.svg` |  |
| 🔧 | `Interaction.svg` |  |
| 🔧 | `InterfaceDefinition.svg` |  |
| 🔧 | `InterfaceUsage.svg` |  |
| 🔧 | `Intersecting.svg` |  |
| 🔧 | `Invariant.svg` |  |
| 🔧 | `InvocationExpression.svg` |  |
| 🔧 | `ItemDefinition.svg` |  |
| 🔧 | `ItemDefinitionIn.svg` |  |
| 🔧 | `ItemDefinitionInout.svg` |  |
| 🔧 | `ItemDefinitionOut.svg` |  |
| 🔧 | `ItemUsage.svg` |  |
| 🔧 | `ItemUsageIn.svg` |  |
| 🔧 | `ItemUsageInout.svg` |  |
| 🔧 | `ItemUsageOut.svg` |  |
| 🔧 | `JoinNode.svg` |  |
| 🔧 | `LibraryPackage.svg` |  |
| 🔧 | `LiteralBoolean.svg` |  |
| 🔧 | `LiteralExpression.svg` |  |
| 🔧 | `LiteralInfinity.svg` |  |
| 🔧 | `LiteralInteger.svg` |  |
| 🔧 | `LiteralRational.svg` |  |
| 🔧 | `LiteralString.svg` |  |
| 🔧 | `Membership.svg` |  |
| 🔧 | `MembershipExpose.svg` |  |
| 🔧 | `MembershipImport.svg` |  |
| 🔧 | `MembershipImportRecursive.svg` |  |
| 🔧 | `MergeNode.svg` |  |
| 🔧 | `Metaclass.svg` |  |
| 🔧 | `MetadataAccessExpression.svg` |  |
| 🔧 | `MetadataDefinition.svg` |  |
| 🔧 | `MetadataFeature.svg` |  |
| 🔧 | `MetadataUsage.svg` |  |
| 🔧 | `Multiplicity.svg` |  |
| 🔧 | `MultiplicityRange.svg` |  |
| 🔧 | `Namespace.svg` |  |
| 🔧 | `NamespaceExpose.svg` |  |
| 🔧 | `NamespaceImport.svg` |  |
| 🔧 | `NamespaceImportRecursive.svg` |  |
| 🔧 | `NullExpression.svg` |  |
| 🔧 | `Objective.svg` |  |
| 🔧 | `ObjectiveMembership.svg` |  |
| 🔧 | `OccurrenceDefinition.svg` |  |
| 🔧 | `OccurrenceUsage.svg` |  |
| 🔧 | `OperatorExpression.svg` |  |
| 🔧 | `OwningMembership.svg` |  |
| 🔧 | `Package.svg` |  |
| 🔧 | `ParameterMembership.svg` |  |
| 🔧 | `PartDefinition.svg` |  |
| 🔧 | `PartUsage.svg` |  |
| 🔧 | `PayloadFeature.svg` |  |
| 🔧 | `PerformActionUsage.svg` |  |
| 🔧 | `PortConjugation.svg` |  |
| 🔧 | `PortDefinition.svg` |  |
| 🔧 | `PortDefinitionIn.svg` |  |
| 🔧 | `PortDefinitionInout.svg` |  |
| 🔧 | `PortDefinitionOut.svg` |  |
| 🔧 | `PortUsage.svg` |  |
| 🔧 | `PortUsageIn.svg` |  |
| 🔧 | `PortUsageInout.svg` |  |
| 🔧 | `PortUsageOut.svg` |  |
| 🔧 | `Predicate.svg` |  |
| 🔧 | `Redefinition.svg` |  |
| 🔧 | `ReferenceDefinition.svg` |  |
| 🔧 | `ReferenceSubsetting.svg` |  |
| 🔧 | `ReferenceUsage.svg` |  |
| 🔧 | `ReferenceUsageIn.svg` |  |
| 🔧 | `ReferenceUsageInout.svg` |  |
| 🔧 | `ReferenceUsageOut.svg` |  |
| 🔧 | `RenderingDefinition.svg` |  |
| 🔧 | `RequirementConstraintMembership.svg` |  |
| 🔧 | `RequirementDefinition.svg` |  |
| 🔧 | `RequirementUsage.svg` |  |
| 🔧 | `RequirementVerificationMembership.svg` |  |
| 🔧 | `ResultExpressionMembership.svg` |  |
| 🔧 | `ReturnParameterMembership.svg` |  |
| 🔧 | `SatisfyRequirementUsage.svg` |  |
| 🔧 | `SelectExpression.svg` |  |
| 🔧 | `SendActionUsage.svg` |  |
| 🔧 | `Specialization.svg` |  |
| 🔧 | `Stakeholder.svg` |  |
| 🔧 | `StakeholderMembership.svg` |  |
| 🔧 | `StateDefinition.svg` |  |
| 🔧 | `StateSubactionMembership.svg` |  |
| 🔧 | `StateUsage.svg` |  |
| 🔧 | `Step.svg` |  |
| 🔧 | `Structure.svg` |  |
| 🔧 | `Subclassification.svg` |  |
| 🔧 | `Subject.svg` |  |
| 🔧 | `SubjectMembership.svg` |  |
| 🔧 | `Subsetting.svg` |  |
| 🔧 | `Succession.svg` |  |
| 🔧 | `SuccessionAsUsage.svg` |  |
| 🔧 | `SuccessionFlow.svg` |  |
| 🔧 | `SuccessionFlowUsage.svg` |  |
| 🔧 | `TerminateActionUsage.svg` |  |
| 🔧 | `TextualRepresentation.svg` |  |
| 🔧 | `TransitionFeatureMembership.svg` |  |
| 🔧 | `TransitionUsage.svg` |  |
| 🔧 | `TriggerInvocationExpression.svg` |  |
| 🔧 | `Type.svg` |  |
| 🔧 | `TypeFeaturing.svg` |  |
| 🔧 | `Unioning.svg` |  |
| 🔧 | `Usage.svg` |  |
| 🔧 | `UseCaseDefinition.svg` |  |
| 🔧 | `UseCaseUsage.svg` |  |
| 🔧 | `VariantMembership.svg` |  |
| 🔧 | `VerificationCaseDefinition.svg` |  |
| 🔧 | `VerificationCaseUsage.svg` |  |
| 🔧 | `ViewDefinition.svg` |  |
| 🔧 | `ViewRenderingMembership.svg` |  |
| 🔧 | `ViewUsage.svg` |  |
| 🔧 | `ViewpointDefinition.svg` |  |
| 🔧 | `ViewpointUsage.svg` |  |
| 🔧 | `WhileLoopActionUsage.svg` |  |
| 🔧 | `plugin.properties` |  |
| ➕ | `plugin_en.properties` |  |
| ➕ | `plugin_zh.properties` |  |
| ➕ | `translate-all.ps1` |  |
| ➕ | `translate-features.sed` |  |
| ➕ | `translate_features.py` |  |
## backend/services/syson-diagram-services/src/main

| 状态 | 文件 | 说明 |
|------|------|------|
| 🔧 | `MultiLineLabelSwitch.java` | 多行标签转换（DoDAF 衍型） |
## backend/services/syson-services/src/main

| 状态 | 文件 | 说明 |
|------|------|------|
| 🔧 | `UtilService.java` | 工具服务（5 种 DoDAF 检测方法） |
| 🔧 | `ViewDefinitionKind.java` | 视图定义类型枚举 |
| 🔧 | `StandardDiagramsConstants.java` | 标准图表常量（DoDAF QN） |
## backend/services/syson-tree-services/src/main

| 状态 | 文件 | 说明 |
|------|------|------|
| 🔧 | `SysONDefaultExplorerServices.java` | 默认资源管理器服务（允许删除表示） |
| 🔧 | `ChangeMarker.svg` |  |
| 🔧 | `FeatureAddition.svg` |  |
| 🔧 | `FeatureDeletion.svg` |  |
| 🔧 | `FeatureModification.svg` |  |
## backend/views/syson-diagram-common-view/src/main

| 状态 | 文件 | 说明 |
|------|------|------|
| 🔧 | `AbstractDefinitionNodeDescriptionProvider.java` | Definition 节点描述（5 种 ConditionalNodeStyles） |
| 🔧 | `AbstractUsageNodeDescriptionProvider.java` | Usage 节点描述（5 种 ConditionalNodeStyles） |
| 🔧 | `AddYourFirstElement.java` | 空图表图片（运行时加载） |
| 🔧 | `AddExistingElements.svg` |  |
| 🔧 | `done_action.svg` |  |
| 🔧 | `start_action.svg` |  |
| 🔧 | `actor.svg` |  |
| 🔧 | `add_your_first_element.svg` |  |
| 🔧 | `content_copy.svg` |  |
| 🔧 | `decision_action.svg` |  |
| 🔧 | `feature_in.svg` |  |
| 🔧 | `feature_inout.svg` |  |
| 🔧 | `feature_out.svg` |  |
| 🔧 | `graphicalDelete.svg` |  |
| 🔧 | `merge_action.svg` |  |
## backend/views/syson-standard-diagrams-view/src/main

| 状态 | 文件 | 说明 |
|------|------|------|
| ➕ | `DoDAFGanttDescriptionProvider.java` | DoDAF 甘特图描述提供者 |
| ➕ | `DoDAFGanttViewDiagramDescriptionProvider.java` |  |
| ➕ | `DoDAFGanttViewJavaServiceProvider.java` | DoDAF 甘特图 Java 服务 |
| ➕ | `DoDAFMatrixTableDescriptionProvider.java` | DoDAF 矩阵表格描述提供者 |
| ➕ | `DoDAFMatrixViewDiagramDescriptionProvider.java` |  |
| ➕ | `DoDAFMatrixViewJavaServiceProvider.java` | DoDAF 矩阵视图 Java 服务 |
| ➕ | `DoDAFSequenceViewDiagramDescriptionProvider.java` |  |
| ➕ | `DoDAFTableDescriptionProvider.java` | DoDAF 表描述提供者 |
| ➕ | `DoDAFTableViewDiagramDescriptionProvider.java` |  |
| ➕ | `DoDAFTableViewJavaServiceProvider.java` | DoDAF 表视图 Java 服务 |
| 🔧 | `SDVDescriptionProvider.java` | SDV 描述提供者（加载空图表图片） |
| ➕ | `DoDAFGanttQueryServices.java` | DoDAF 甘特图查询服务 |
| ➕ | `DoDAFGanttViewCreateService.java` |  |
| ➕ | `DoDAFMatrixMutationServices.java` | DoDAF 矩阵变更服务（文档持久化） |
| ➕ | `DoDAFMatrixQueryServices.java` | DoDAF 矩阵查询服务（CRUD） |
| ➕ | `DoDAFMatrixViewCreateService.java` |  |
| ➕ | `DoDAFSequenceViewCreateService.java` |  |
| ➕ | `DoDAFTableQueryServices.java` | DoDAF 表查询服务 |
| ➕ | `DoDAFTableViewCreateService.java` |  |
## backend/views/syson-table-requirements-view/src/main

| 状态 | 文件 | 说明 |
|------|------|------|
| 🔧 | `RTVTableDescriptionProvider.java` |  |
| 🔧 | `createRequirement.svg` |  |
| 🔧 | `importRequirements.svg` |  |
| 🔧 | `semanticDelete.svg` |  |
## backend/views/syson-tree-explorer-view/src/main

| 状态 | 文件 | 说明 |
|------|------|------|
| 🔧 | `LibraryResource.svg` |  |
| 🔧 | `Resource.svg` |  |
## doc

| 状态 | 文件 | 说明 |
|------|------|------|
| ➕ | `detaileddesign.md` |  |
## frontend/syson-components/i18n/en

| 状态 | 文件 | 说明 |
|------|------|------|
| ➕ | `sirius-components-core.json` |  |
| ➕ | `sirius-components-deck.json` |  |
| ➕ | `sirius-components-diagrams.json` |  |
| ➕ | `sirius-components-formdescriptioneditors.json` |  |
| ➕ | `sirius-components-forms.json` |  |
| ➕ | `sirius-components-gantt.json` |  |
| ➕ | `sirius-components-palette.json` |  |
| ➕ | `sirius-components-portals.json` |  |
| ➕ | `sirius-components-selection.json` |  |
| ➕ | `sirius-components-tables.json` |  |
| ➕ | `sirius-components-trees.json` |  |
| ➕ | `sirius-components-validation.json` |  |
| ➕ | `sirius-components-widget-reference.json` |  |
| ➕ | `sirius-web-application.json` |  |
## frontend/syson-components/i18n/zh

| 状态 | 文件 | 说明 |
|------|------|------|
| ➕ | `sirius-components-core.json` |  |
| ➕ | `sirius-components-deck.json` |  |
| ➕ | `sirius-components-diagrams.json` |  |
| ➕ | `sirius-components-formdescriptioneditors.json` |  |
| ➕ | `sirius-components-forms.json` |  |
| ➕ | `sirius-components-gantt.json` |  |
| ➕ | `sirius-components-palette.json` |  |
| ➕ | `sirius-components-portals.json` |  |
| ➕ | `sirius-components-selection.json` |  |
| ➕ | `sirius-components-tables.json` |  |
| ➕ | `sirius-components-trees.json` |  |
| ➕ | `sirius-components-validation.json` |  |
| ➕ | `sirius-components-widget-reference.json` |  |
| ➕ | `sirius-web-application.json` |  |
## frontend/syson-components/src/extensions

| 状态 | 文件 | 说明 |
|------|------|------|
| 🔧 | `InsertTextualSysMLv2MenuContribution.tsx` |  |
| 🔧 | `SysONDiagramPanelMenu.tsx` |  |
## frontend/syson-components/src/extensions/expressions

| 状态 | 文件 | 说明 |
|------|------|------|
| 🔧 | `DeleteSysMLExpressionMenuContribution.tsx` |  |
| 🔧 | `EditSysMLExpressionMenuContribution.tsx` |  |
| 🔧 | `NewSysMLExpressionMenuContribution.tsx` |  |
## frontend/syson-components/src/extensions/registry

| 状态 | 文件 | 说明 |
|------|------|------|
| 🔧 | `SysONExtensionRegistry.tsx` | 扩展注册表（中文 Apollo Link） |
| 🔧 | `SysONNodeTypeRegistry.tsx` | 节点类型注册表（5 种 DoDAF 节点） |
## frontend/syson-components/src/nodes

| 状态 | 文件 | 说明 |
|------|------|------|
| ➕ | `Capability.svg` | DoDAF Capability 图标 |
| ➕ | `InformationExchange.svg` | DoDAF Exchange 图标 |
| ➕ | `OperationalNode.svg` | DoDAF Operational 图标 |
| ➕ | `Organization.svg` | DoDAF Organization 图标 |
| 🔧 | `SysMLNodesDocumentTransform.ts` | SysML 节点文档转换 |
| ➕ | `SystemNode.svg` | DoDAF System 图标 |
## frontend/syson-components/src/nodes/dodaf_capability

| 状态 | 文件 | 说明 |
|------|------|------|
| ➕ | `DodafCapability.tsx` | DoDAF 能力节点 React 组件 |
| ➕ | `DodafCapability.types.ts` | DoDAF 能力节点类型定义 |
| ➕ | `DodafCapabilityConverter.ts` | DoDAF 能力节点转换器 |
| ➕ | `DodafCapabilityLayoutHandler.ts` | DoDAF 能力节点布局处理器 |
| ➕ | `DodafCapabilityPaletteAppearanceSection.tsx` |  |
| ➕ | `DodafCapabilityPaletteAppearanceSection.types.ts` |  |
| ➕ | `DodafCapabilityPart.tsx` |  |
| ➕ | `DodafCapabilityPart.types.ts` |  |
| ➕ | `useUpdateDodafCapabilityAppearance.ts` |  |
| ➕ | `useUpdateDodafCapabilityAppearance.types.ts` |  |
## frontend/syson-components/src/nodes/dodaf_information_exchange

| 状态 | 文件 | 说明 |
|------|------|------|
| ➕ | `DodafInformationExchange.tsx` | DoDAF 信息交换节点 React 组件 |
| ➕ | `DodafInformationExchange.types.ts` | DoDAF 信息交换节点类型定义 |
| ➕ | `DodafInformationExchangeConverter.ts` | DoDAF 信息交换节点转换器 |
| ➕ | `DodafInformationExchangeLayoutHandler.ts` | DoDAF 信息交换节点布局处理器 |
| ➕ | `DodafInformationExchangePaletteAppearanceSection.tsx` |  |
| ➕ | `DodafInformationExchangePaletteAppearanceSection.types.ts` |  |
| ➕ | `DodafInformationExchangePart.tsx` |  |
| ➕ | `DodafInformationExchangePart.types.ts` |  |
| ➕ | `useUpdateDodafInformationExchangeAppearance.ts` |  |
| ➕ | `useUpdateDodafInformationExchangeAppearance.types.ts` |  |
## frontend/syson-components/src/nodes/dodaf_operational_node

| 状态 | 文件 | 说明 |
|------|------|------|
| ➕ | `DodafOperationalNode.tsx` | DoDAF 操作节点 React 组件 |
| ➕ | `DodafOperationalNode.types.ts` | DoDAF 操作节点类型定义 |
| ➕ | `DodafOperationalNodeConverter.ts` | DoDAF 操作节点转换器 |
| ➕ | `DodafOperationalNodeLayoutHandler.ts` | DoDAF 操作节点布局处理器 |
| ➕ | `DodafOperationalNodePaletteAppearanceSection.tsx` |  |
| ➕ | `DodafOperationalNodePaletteAppearanceSection.types.ts` |  |
| ➕ | `DodafOperationalNodePart.tsx` |  |
| ➕ | `DodafOperationalNodePart.types.ts` |  |
| ➕ | `useUpdateDodafOperationalNodeAppearance.ts` |  |
| ➕ | `useUpdateDodafOperationalNodeAppearance.types.ts` |  |
## frontend/syson-components/src/nodes/dodaf_organization

| 状态 | 文件 | 说明 |
|------|------|------|
| ➕ | `DodafOrganization.tsx` | DoDAF 组织节点 React 组件 |
| ➕ | `DodafOrganization.types.ts` | DoDAF 组织节点类型定义 |
| ➕ | `DodafOrganizationConverter.ts` | DoDAF 组织节点转换器 |
| ➕ | `DodafOrganizationLayoutHandler.ts` | DoDAF 组织节点布局处理器 |
| ➕ | `DodafOrganizationPaletteAppearanceSection.tsx` |  |
| ➕ | `DodafOrganizationPaletteAppearanceSection.types.ts` |  |
| ➕ | `DodafOrganizationPart.tsx` |  |
| ➕ | `DodafOrganizationPart.types.ts` |  |
| ➕ | `useUpdateDodafOrganizationAppearance.ts` |  |
| ➕ | `useUpdateDodafOrganizationAppearance.types.ts` |  |
## frontend/syson-components/src/nodes/dodaf_system_node

| 状态 | 文件 | 说明 |
|------|------|------|
| ➕ | `DodafSystemNode.tsx` | DoDAF 系统节点 React 组件 |
| ➕ | `DodafSystemNode.types.ts` | DoDAF 系统节点类型定义 |
| ➕ | `DodafSystemNodeConverter.ts` | DoDAF 系统节点转换器 |
| ➕ | `DodafSystemNodeLayoutHandler.ts` | DoDAF 系统节点布局处理器 |
| ➕ | `DodafSystemNodePaletteAppearanceSection.tsx` |  |
| ➕ | `DodafSystemNodePaletteAppearanceSection.types.ts` |  |
| ➕ | `DodafSystemNodePart.tsx` |  |
| ➕ | `DodafSystemNodePart.types.ts` |  |
| ➕ | `useUpdateDodafSystemNodeAppearance.ts` |  |
| ➕ | `useUpdateDodafSystemNodeAppearance.types.ts` |  |
## frontend/syson

| 状态 | 文件 | 说明 |
|------|------|------|
| 🔧 | `index.html` | 主页面（fetch/XHR 拦截 + DOM 翻译观察器） |
## frontend/syson/src/background

| 状态 | 文件 | 说明 |
|------|------|------|
| ➕ | `FloatingLines.tsx` |  |
| ➕ | `HomepageBackground.tsx` |  |
## frontend/syson/src

| 状态 | 文件 | 说明 |
|------|------|------|
| ➕ | `dodaf-views.css` | DoDAF 视图样式（深色背景、表格样式） |
## frontend/syson/src/extensions

| 状态 | 文件 | 说明 |
|------|------|------|
| 🔧 | `SysONFooter.tsx` | 页脚组件 |
| 🔧 | `SysONNavigationBarIcon.tsx` |  |
## frontend/syson/src

| 状态 | 文件 | 说明 |
|------|------|------|
| 🔧 | `index.tsx` | 应用入口（导入 dodaf-views.css） |
## frontend/syson/src/theme

| 状态 | 文件 | 说明 |
|------|------|------|
| 🔧 | `sysonTheme.ts` | MUI 主题（深色科技风格） |
## frontend/syson/src

| 状态 | 文件 | 说明 |
|------|------|------|
| ➕ | `transparency.css` |  |
| 🔧 | `variables.css` |  |
## frontend/syson

| 状态 | 文件 | 说明 |
|------|------|------|
| 🔧 | `vite.config.js` | Vite 配置（甘特图补丁插件） |
## 

| 状态 | 文件 | 说明 |
|------|------|------|
| 🔧 | `package-lock.json` |  |
| 🔧 | `package.json` |  |
| ➕ | `patch-i18n.bat` |  |
| ➕ | `patch-i18n.ps1` |  |
## scripts

| 状态 | 文件 | 说明 |
|------|------|------|
| ➕ | `generate-dodaf-nodes.ps1` |  |