import re

file = r"E:\syson-rg\syson\backend\metamodel\syson-sysml-metamodel-edit\src\main\resources\plugin_zh.properties"
with open(file, "r", encoding="utf-8") as f:
    lines = f.readlines()

# Translation map: English label -> Chinese
T = {
    # Comment
    "Body": "正文", "Locale": "语言环境",
    # Documentation
    "Documented Element": "被文档化元素",
    # Visibility
    "Visibility": "可见性",
    # Kind
    "Kind": "类型",
    # Type
    "Type": "类型",
    # General
    "General": "通用", "Specific": "特定",
    # Owned/Owning
    "Owned Element": "拥有的元素", "Owning Element": "所属元素",
    "Owned Relationship": "拥有的关系", "Owning Relationship": "所属关系",
    "Owned Member": "拥有的成员",
    "Owning Namespace": "所属命名空间",
    "Owned Import": "拥有的导入",
    # Membership common
    "Member Element": "成员元素", "Member Name": "成员名称", "Member Short Name": "成员简称",
    "Member Element Id": "成员元素ID",
    "Imported Membership": "被导入成员", "Imported Namespace": "被导入命名空间",
    # Feature common
    "Feature": "特性", "Directed Feature": "定向特性",
    "End Feature": "端点特性", "Inherited Feature": "继承的特性",
    "Feature Membership": "特性成员", "Inherited Membership": "继承的成员",
    "Multiplicity": "多重性", "Input": "输入", "Output": "输出",
    # Connector / Association
    "Association": "关联", "Connector End": "连接器端点",
    "Related Feature": "关联特性", "Source Feature": "源特性", "Target Feature": "目标特性",
    "Default Featuring Type": "默认特性化类型",
    "Related Type": "关联类型", "Source Type": "源类型", "Target Type": "目标类型",
    "Association End": "关联端点",
    # Flow
    "Flow End": "流端点", "Payload Feature": "载荷特性", "Payload Type": "载荷类型",
    "Source Output Feature": "源输出特性", "Target Input Feature": "目标输入特性",
    "Interaction": "交互",
    # Connection / Interface
    "Connection End": "连接端点", "Interface End": "接口端点",
    # Parameter
    "Parameter": "参数", "Step": "步骤",
    # Element common
    "Owned Member Element": "拥有的成员元素",
    "Owned Member Element Id": "拥有的成员元素ID",
    "Owned Member Name": "拥有的成员名称",
    "Owned Member Short Name": "拥有的成员简称",
    "Member": "成员", "Membership": "成员关系",
    "Owned Membership": "拥有的成员关系",
    "Owned Annotation": "拥有的注释",
    "Owned Relationship": "拥有的关系",
    "Owner": "所有者",
    "Owning Membership": "所属成员关系",
    "Owning Relationship": "所属关系",
    "Textual Representation": "文本表示",
    # Definition nested
    "Owned Action": "拥有的动作", "Owned Allocation": "拥有的分配",
    "Owned Analysis Case": "拥有的分析案例", "Owned Attribute": "拥有的属性",
    "Owned Calculation": "拥有的计算", "Owned Case": "拥有的案例",
    "Owned Concern": "拥有的关注点", "Owned Connection": "拥有的连接",
    "Owned Constraint": "拥有的约束", "Owned Enumeration": "拥有的枚举",
    "Owned Flow": "拥有的流", "Owned Interface": "拥有的接口",
    "Owned Item": "拥有的项", "Owned Metadata": "拥有的元数据",
    "Owned Occurrence": "拥有的事件", "Owned Part": "拥有的部件",
    "Owned Port": "拥有的端口", "Owned Reference": "拥有的引用",
    "Owned Rendering": "拥有的渲染", "Owned Requirement": "拥有的需求",
    "Owned State": "拥有的状态", "Owned Transition": "拥有的转换",
    "Owned Usage": "拥有的使用", "Owned Use Case": "拥有的用例",
    "Owned Verification Case": "拥有的验证案例",
    "Owned View": "拥有的视图", "Owned Viewpoint": "拥有的视点",
    "Usage": "使用", "Variant": "变体", "Variant Membership": "变体成员",
    "Definition": "定义",
    # Annotating
    "Annotated Element": "被注释元素", "Annotation": "注释",
    "Annotating Element": "注释元素",
    "Owned Annotating Relationship": "拥有的注释关系",
    "Owned Annotating Element": "拥有的注释元素",
    # Redefinition/Subsetting
    "Redefined Feature": "被重定义特性", "Redefining Feature": "重定义特性",
    "Referenced Feature": "被引用特性", "Referencing Feature": "引用特性",
    "Subsetted Feature": "被子集特性", "Subsetting Feature": "子集特性",
    "Owning Feature": "所属特性",
    # FeatureChaining/Inverting
    "Chaining Feature": "链式特性", "Feature Chained": "被链特性",
    "Feature Inverted": "被反转特性", "Inverting Feature": "反转特性",
    "Crossed Feature": "被交叉特性", "Crossing Feature": "交叉特性",
    # Typing
    "Owning Feature Membership": "所属特性成员",
    "Typed Feature": "被类型化特性",
    "Owned Member Feature": "拥有的成员特性",
    "Owning Type": "所属类型",
    "Featuring Type": "特性化类型", "Feature Of Type": "类型特性",
    "Owning Feature Of Type": "所属类型特性",
    # Type features
    "Owned Conjugator": "拥有的共轭器",
    "Owned Differencing": "拥有的差异", "Owned Disjoining": "拥有的分离",
    "Owned End Feature": "拥有的端点特性",
    "Owned Intersecting": "拥有的交叉", "Owned Specialization": "拥有的特化",
    "Owned Unioning": "拥有的联合",
    "Differencing Type": "差异类型", "Type Differenced": "被差异类型",
    "Disjoining Type": "分离类型", "Type Disjoined": "被分离类型",
    "Intersecting Type": "交叉类型", "Type Intersected": "被交叉类型",
    "Unioning Type": "联合类型", "Type Unioned": "被联合类型",
    "Owning Classifier": "所属分类器",
    "Subclassifier": "子分类器", "Superclassifier": "超分类器",
    "Owned Subclassification": "拥有的子分类",
    # Conjugation
    "Conjugated Type": "共轭类型", "Original Type": "原始类型",
    # Cross Subsetting
    "Owned Cross Subsetting": "拥有的交叉子集",
    "Owned Feature Chaining": "拥有的特性链",
    "Owned Feature Inverting": "拥有的特性反转",
    "Owned Redefinition": "拥有的重定义",
    "Owned Reference Subsetting": "拥有的引用子集",
    "Owned Subsetting": "拥有的子集",
    "Owned Type Featuring": "拥有的类型特性化",
    "Owned Typing": "拥有的类型化",
    "End Owning Type": "端点所属类型",
    "Feature Target": "特性目标",
    # FeatureValue
    "Feature With Value": "带值特性", "Value": "值",
    # Dependency
    "Client": "客户端", "Supplier": "提供者",
    # Expression
    "Function": "函数", "Result": "结果",
    "Is Model Level Evaluable": "模型级可评估",
    "Expression": "表达式",
    "Referent": "指称对象",
    "Target Feature": "目标特性",
    "Argument": "参数", "Instantiated Type": "实例化类型",
    # FlowDefinition/Usage
    "Flow Definition": "流定义",
    # For/If/Loop action
    "Loop Variable": "循环变量", "Seq Argument": "序列参数",
    "Else Action": "否则动作", "If Argument": "条件参数", "Then Action": "则动作",
    "Body Action": "循环体动作",
    # FramedConcern
    "Referenced Concern": "被引用关注点",
    # Import
    "Is Import All": "导入全部", "Is Recursive": "递归",
    "Imported Element": "被导入元素", "Import Owning Namespace": "导入所属命名空间",
    "Use Case Included": "被包含用例",
    # Invariant
    "Is Negated": "否定",
    # Item/Interface/Connection definition
    "Item Definition": "项定义", "Interface Definition": "接口定义",
    "Connection Definition": "连接定义",
    # Library
    "Is Standard": "标准",
    # Loop
    # Metadata
    "Metaclass": "元类", "Metadata Definition": "元数据定义",
    "Referenced Element": "被引用元素",
    # MultiplicityRange
    "Bound": "界限", "Lower Bound": "下限", "Upper Bound": "上限",
    # Occurrence
    "Is Individual": "个体", "Portion Kind": "部分类型",
    "Individual Definition": "个体定义", "Occurrence Definition": "事件定义",
    # Operator
    "Operator": "运算符",
    # Package
    "Filter Condition": "过滤条件",
    # Parameter members
    "Owned Member Parameter": "拥有的成员参数",
    # Part/Port definitions
    "Part Definition": "部件定义", "Port Definition": "端口定义",
    "Performed Action": "执行的动作",
    "Conjugated Port Definition": "共轭端口定义",
    "Original Port Definition": "原始端口定义",
    "Owned Port Conjugator": "拥有的端口共轭器",
    # Rendering
    "Rendering": "渲染", "Rendering Definition": "渲染定义",
    # Requirement
    "Req Id": "需求ID", "Text": "文本",
    "Actor Parameter": "角色参数", "Assumed Constraint": "假设约束",
    "Framed Concern": "框架关注点", "Required Constraint": "必需约束",
    "Stakeholder Parameter": "利益相关方参数", "Subject Parameter": "主体参数",
    "Requirement Definition": "需求定义",
    "Owned Constraint": "拥有的约束", "Referenced Constraint": "被引用约束",
    "Owned Requirement": "拥有的需求", "Verified Requirement": "已验证需求",
    "Owned Objective Requirement": "拥有的目标需求",
    "Owned Result Expression": "拥有的结果表达式",
    # Satisfy/Send
    "Satisfied Requirement": "被满足需求", "Satisfying Feature": "满足特性",
    "Payload Argument": "载荷参数", "Payload Parameter": "载荷形参",
    "Receiver Argument": "接收方参数", "Sender Argument": "发送方参数",
    # Specialization
    "Owning Feature": "所属特性",
    # State
    "Is Parallel": "并行", "Do Action": "执行动作",
    "Entry Action": "入口动作", "Exit Action": "退出动作",
    "State": "状态", "State Definition": "状态定义",
    # Step/Behavior
    "Behavior": "行为",
    # Subclassification
    "Owned Subject Parameter": "拥有的主体参数",
    "Owned Variant Usage": "拥有的变体使用",
    "Terminated Occurrence Argument": "终止事件参数",
    "Transition Feature": "转换特性",
    "Effect Action": "效果动作", "Guard Expression": "守卫表达式",
    "Succession": "继承",
    "Source": "源", "Target": "目标",
    "Trigger Action": "触发动作",
    # TypeFeaturing
    "Represented Element": "被表示元素", "Language": "语言",
    # UseCase
    "Use Case Definition": "用例定义", "Included Use Case": "被包含用例",
    # VerificationCase
    "Verification Case Definition": "验证案例定义",
    # View
    "Satisfied Viewpoint": "满足的视点", "View Condition": "视图条件",
    "View Rendering": "视图渲染", "View Definition": "视图定义",
    "Viewpoint Stakeholder": "视点利益相关方", "Viewpoint Definition": "视点定义",
    "Exposed Element": "暴露的元素",
    "Owned Rendering": "拥有的渲染", "Referenced Rendering": "被引用渲染",
    # While
    "Until Argument": "直到参数", "While Argument": "当参数",
    # AcceptAction
    # ActionDefinition/ActionUsage
    "Action": "动作", "Action Definition": "动作定义",
    # ActorMembership
    "Owned Actor Parameter": "拥有的角色参数",
    # Allocation
    "Allocation": "分配", "Allocation Definition": "分配定义",
    # AnalysisCase
    "Analysis Case Definition": "分析案例定义", "Result Expression": "结果表达式",
    # AssertConstraint
    "Asserted Constraint": "断言约束",
    # AssignmentAction
    "Target Argument": "目标参数", "Value Expression": "值表达式",
    # Attribute
    "Attribute Definition": "属性定义",
    # BooleanExpression
    "Predicate": "谓词",
    # Calculation
    "Calculation": "计算", "Calculation Definition": "计算定义",
    # Case
    "Case Definition": "案例定义",
    # Concern
    "Concern Definition": "关注点定义",
    # Constraint
    "Constraint Definition": "约束定义",
    # Enumeration
    "Enumerated Value": "枚举值", "Enumeration Definition": "枚举定义",
    # EventOccurrence
    "Event Occurrence": "事件发生",
    # ExhibitState
    "Exhibited State": "展示状态",
    # These 7 were missed in first run
    "Objective Requirement": "目标需求",
    "Condition": "条件",
    "Is Default": "默认值",
    "Is Initial": "初始值",
    "Owned Stakeholder Parameter": "拥有的利益相关方参数",
    "View": "视图",
    # Misc
    "Unspecified": "未指定",
}

# Type name translations
TT = {
    "Accept Action": "Accept Action", "Action Definition": "Action Definition",
    "Action": "Action", "Actor Membership": "Actor Membership",
    "Allocation Definition": "Allocation Definition", "Allocation": "Allocation",
    "Analysis Case Definition": "Analysis Case Definition",
    "Analysis Case": "Analysis Case",
    "Annotating Element": "Annotating Element", "Annotation": "注释",
    "Assert Constraint": "Assert Constraint",
    "Assignment Action": "Assignment Action",
    "Association": "Association", "Association Structure": "Association Structure",
    "Attribute Definition": "Attribute Definition", "Attribute": "Attribute",
    "Behavior": "Behavior", "Binding Connector": "Binding Connector",
    "Binding Connector As Usage": "Binding Connector As Usage",
    "Boolean Expression": "Boolean Expression",
    "Calculation Definition": "Calculation Definition", "Calculation": "Calculation",
    "Case Definition": "Case Definition", "Case": "Case",
    "Class": "Class", "Classifier": "Classifier",
    "Collect Expression": "Collect Expression",
    "Comment": "注释",
    "Concern Definition": "Concern Definition", "Concern": "Concern",
    "Conjugated Port Definition": "Conjugated Port Definition",
    "Conjugated Port Typing": "Conjugated Port Typing",
    "Conjugation": "Conjugation",
    "Connection Definition": "Connection Definition", "Connection": "Connection",
    "Connector": "Connector", "Connector As Usage": "Connector As Usage",
    "Constraint Definition": "Constraint Definition", "Constraint Usage": "Constraint Usage",
    "Constructor Expression": "Constructor Expression",
    "Control Node": "Control Node",
    "Cross Subsetting": "Cross Subsetting",
    "Data Type": "Data Type", "Decision Node": "Decision Node",
    "Definition": "Definition", "Dependency": "Dependency",
    "Differencing": "Differencing", "Disjoining": "Disjoining",
    "Documentation": "文档",
    "Element": "Element", "Element Filter Membership": "Element Filter Membership",
    "End Feature Membership": "End Feature Membership",
    "Enumeration Definition": "Enumeration Definition", "Enumeration": "Enumeration",
    "Event Occurrence": "Event Occurrence",
    "Exhibit State": "Exhibit State", "Expose": "Expose",
    "Expression": "Expression",
    "Feature": "Feature", "Feature Chain Expression": "Feature Chain Expression",
    "Feature Chaining": "Feature Chaining", "Feature Inverting": "Feature Inverting",
    "Feature Membership": "Feature Membership",
    "Feature Reference Expression": "Feature Reference Expression",
    "Feature Typing": "Feature Typing", "Feature Value": "Feature Value",
    "Flow": "Flow", "Flow Definition": "Flow Definition",
    "Flow End": "Flow End", "Flow Usage": "Flow Usage",
    "Fork Node": "Fork Node", "For Loop Action": "For Loop Action",
    "Framed Concern Membership": "Framed Concern Membership",
    "Function": "Function", "If Action": "If Action",
    "Import": "Import", "Include Use Case": "Include Use Case",
    "Index Expression": "Index Expression",
    "Instantiation Expression": "Instantiation Expression",
    "Interaction": "Interaction",
    "Interface Definition": "Interface Definition", "Interface": "Interface",
    "Intersecting": "Intersecting", "Invariant": "Invariant",
    "Invocation Expression": "Invocation Expression",
    "Item Definition": "Item Definition", "Item": "Item",
    "Join Node": "Join Node",
    "Library Package": "Library Package",
    "Literal Boolean": "Literal Boolean", "Literal Expression": "Literal Expression",
    "Literal Infinity": "Literal Infinity", "Literal Integer": "Literal Integer",
    "Literal Rational": "Literal Rational", "Literal String": "Literal String",
    "Loop Action": "Loop Action",
    "Membership": "Membership", "Membership Expose": "Membership Expose",
    "Membership Import": "Membership Import",
    "Merge Node": "Merge Node", "Metaclass": "Metaclass",
    "Metadata Access Expression": "Metadata Access Expression",
    "Metadata Definition": "Metadata Definition",
    "Metadata Feature": "Metadata Feature", "Metadata": "Metadata",
    "Multiplicity": "Multiplicity", "Multiplicity Range": "Multiplicity Range",
    "Namespace": "Namespace", "Namespace Expose": "Namespace Expose",
    "Namespace Import": "Namespace Import",
    "Null Expression": "Null Expression",
    "Objective Membership": "Objective Membership",
    "Occurrence Definition": "Occurrence Definition", "Occurrence": "Occurrence",
    "Operator Expression": "Operator Expression",
    "Owning Membership": "Owning Membership",
    "Package": "Package", "Parameter Membership": "Parameter Membership",
    "Part Definition": "Part Definition", "Part": "Part",
    "Payload Feature": "Payload Feature",
    "Perform Action": "Perform Action",
    "Port Conjugation": "Port Conjugation",
    "Port Definition": "Port Definition", "Port": "Port",
    "Predicate": "Predicate",
    "Redefinition": "Redefinition",
    "Reference Subsetting": "Reference Subsetting", "Reference": "Reference",
    "Relationship": "Relationship",
    "Rendering Definition": "Rendering Definition", "Rendering": "Rendering",
    "Requirement Constraint Membership": "Requirement Constraint Membership",
    "Requirement Definition": "Requirement Definition", "Requirement": "Requirement",
    "Requirement Verification Membership": "Requirement Verification Membership",
    "Result Expression Membership": "Result Expression Membership",
    "Return Parameter Membership": "Return Parameter Membership",
    "Satisfy Requirement": "Satisfy Requirement",
    "Select Expression": "Select Expression",
    "Send Action": "Send Action", "Specialization": "Specialization",
    "Stakeholder Membership": "Stakeholder Membership",
    "State Definition": "State Definition",
    "State Subaction Membership": "State Subaction Membership",
    "State": "State", "Step": "Step", "Structure": "Structure",
    "Subclassification": "Subclassification",
    "Subject Membership": "Subject Membership", "Subsetting": "Subsetting",
    "Succession": "Succession", "Succession As Usage": "Succession As Usage",
    "Succession Flow": "Succession Flow",
    "Terminate Action": "Terminate Action",
    "Textual Representation": "Textual Representation",
    "Transition Feature Membership": "Transition Feature Membership",
    "Transition": "Transition",
    "Trigger Invocation Expression": "Trigger Invocation Expression",
    "Type": "Type", "Type Featuring": "Type Featuring",
    "Unioning": "Unioning", "Usage": "Usage",
    "Use Case Definition": "Use Case Definition", "Use Case": "Use Case",
    "Variant Membership": "Variant Membership",
    "Verification Case Definition": "Verification Case Definition",
    "Verification Case": "Verification Case",
    "View Definition": "View Definition", "Viewpoint Definition": "Viewpoint Definition",
    "Viewpoint": "Viewpoint",
    "View Rendering Membership": "View Rendering Membership",
    "View": "视图",
    "While Loop Action": "While Loop Action",
    "Object": "对象", "Value": "值",
}

out = []
for line in lines:
    mf = re.match(r"^(.*_feature\s*=\s*)(.+)$", line)
    mt = re.match(r"^(.*_type\s*=\s*)(.+)$", line)
    md = re.match(r"^(.*_datatype\s*=\s*)(.+)$", line)
    if mf:
        prefix = mf.group(1)
        eng = mf.group(2).strip()
        chn = T.get(eng)
        if chn:
            out.append(f"{prefix}{chn}\n")
        else:
            out.append(line)
    elif mt:
        prefix = mt.group(1)
        eng = mt.group(2).strip()
        chn = TT.get(eng)
        if chn:
            out.append(f"{prefix}{chn}\n")
        else:
            out.append(line)
    elif md:
        prefix = md.group(1)
        eng = md.group(2).strip()
        chn = TT.get(eng)
        if chn:
            out.append(f"{prefix}{chn}\n")
        else:
            out.append(line)
    else:
        out.append(line)

with open(file, "w", encoding="utf-8") as f:
    f.writelines(out)

# Count remaining English labels
remaining = 0
for line in out:
    if re.match(r"^.*_feature\s*=\s*[A-Z]", line):
        remaining += 1
        print(f"  UNTRANSLATED: {line.strip()}")

print(f"\nRemaining English labels: {remaining}")
print("Done!")
