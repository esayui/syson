package org.eclipse.syson.application.sysmlv2;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.syson.sysml.SysmlFactory;
import org.eclipse.syson.sysml.metamodel.util.ElementUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Builds DoDAF v2.0 template resources with element relationships and expose.
 */
public class Dodafv2TemplateBuilder {

    private final Logger logger = LoggerFactory.getLogger(Dodafv2TemplateBuilder.class);

    public Resource buildLibraryResource(Resource emptyResource) {
        var rootNamespace = SysmlFactory.eINSTANCE.createNamespace();
        emptyResource.getContents().add(rootNamespace);
        var libraryPackage = SysmlFactory.eINSTANCE.createLibraryPackage();
        libraryPackage.setDeclaredName("DoDAFv2_Library");
        libraryPackage.setElementId(ElementUtil.generateUUID(libraryPackage).toString());
        this.addToNamespace(rootNamespace, libraryPackage);
        this.createMetadataDef(libraryPackage, "DoDAF_ViewpointKind");
        this.createMetadataDef(libraryPackage, "DoDAF_ModelType");
        this.createMetadataDef(libraryPackage, "DoDAF_OperationalNode");
        this.createMetadataDef(libraryPackage, "DoDAF_SystemNode");
        this.createMetadataDef(libraryPackage, "DoDAF_Capability");
        this.createMetadataDef(libraryPackage, "DoDAF_Organization");
        this.createMetadataDef(libraryPackage, "DoDAF_InformationFlow");
        return emptyResource;
    }

    public Resource buildProjectResource(Resource emptyResource) {
        var rootNamespace = SysmlFactory.eINSTANCE.createNamespace();
        emptyResource.getContents().add(rootNamespace);

        var rootPkg = this.createPackage(null, "有人无人协同反潜系统");
        this.addToNamespace(rootNamespace, rootPkg);

        // AV
        var avPkg = this.createPackage(rootPkg, "AV_全视角");
        this.createComment(avPkg, "AV-1_概述", "本体系架构描述有人/无人协同反潜作战系统");
        this.createComment(avPkg, "AV-2_综合词典", "UUV:无人潜航器 TAS:拖曳阵列声呐 VDS:变深声呐");

        // CV
        var cvPkg = this.createPackage(rootPkg, "CV_能力视角");
        var cv1 = this.createViewUsage(cvPkg, "CV-1_能力构想");
        var aswCap = this.addChild(cv1, this.createPartDef("反潜作战能力"));
        var detectCap = this.addChild(cv1, this.createPartUsage("水下目标探测能力"));

        var cv2 = this.createViewUsage(cvPkg, "CV-2_能力分类");
        var cv2Det = this.addChild(cv2, this.createPartDef("探测感知能力"));
        var cv2Cmd = this.addChild(cv2, this.createPartDef("指挥控制能力"));
        var cv2Atk = this.addChild(cv2, this.createPartDef("打击能力"));
        var cv2Sup = this.addChild(cv2, this.createPartDef("保障能力"));

        // OV
        var ovPkg = this.createPackage(rootPkg, "OV_作战视角");

        var ov1 = this.createViewUsage(ovPkg, "OV-1_高层作战概念图");
        var ship = this.addChild(ov1, this.createPartUsage("水面指挥舰"));
        var uuv = this.addChild(ov1, this.createPartUsage("无人潜航器编队"));
        var air = this.addChild(ov1, this.createPartUsage("反潜巡逻机"));
        var cmd = this.addChild(ov1, this.createPartUsage("岸基指挥中心"));
        var sen = this.addChild(ov1, this.createPartUsage("水下传感器阵列"));
        var enemy = this.addChild(ov1, this.createPartUsage("敌方潜艇目标"));

        var ov2 = this.createViewUsage(ovPkg, "OV-2_作战资源流描述");
        var cmdNode = this.addChild(ov2, this.createPartUsage("指挥节点"));
        var detNode = this.addChild(ov2, this.createPartUsage("探测节点"));
        var atkNode = this.addChild(ov2, this.createPartUsage("攻击节点"));

        // Flow: 探测 -> 指挥

        var ov4 = this.createViewUsage(ovPkg, "OV-4_组织结构图");
        var jtCmd = this.addChild(ov4, this.createPartUsage("联合反潜指挥部"));
        var swGrp = this.addChild(ov4, this.createPartUsage("水面作战群"));
        var airGrp = this.addChild(ov4, this.createPartUsage("航空反潜大队"));
        var uuvUnit = this.addChild(ov4, this.createPartUsage("水下无人系统分队"));


        var ov5a = this.createViewUsage(ovPkg, "OV-5a_作战活动分解");
        var asw = this.addChild(ov5a, this.createActionUsage("反潜作战"));
        var detect = this.addChild(ov5a, this.createActionUsage("搜索探测"));
        var track = this.addChild(ov5a, this.createActionUsage("识别跟踪"));
        var attack = this.addChild(ov5a, this.createActionUsage("攻击决策"));
        var eval = this.addChild(ov5a, this.createActionUsage("效果评估"));
        var support = this.addChild(ov5a, this.createActionUsage("战场保障"));
        // Succession flow: 搜索探测 -> 识别跟踪 -> 攻击决策 -> 效果评估

        // SV
        var svPkg = this.createPackage(rootPkg, "SV_系统视角");
        var sv1 = this.createViewUsage(svPkg, "SV-1_系统接口描述");
        var c2 = this.addChild(sv1, this.createPartDef("舰载指控系统"));
        var sonar = this.addChild(sv1, this.createPartDef("声呐系统"));
        var weapon = this.addChild(sv1, this.createPartDef("武器系统"));
        var comm = this.addChild(sv1, this.createPartDef("通信系统"));
        var nav = this.addChild(sv1, this.createPartDef("导航系统"));
        var ums = this.addChild(sv1, this.createPartDef("无人系统"));
        // Dependencies: 声呐 -> 指控, 指控 -> 武器, 通信 -> 指控

        var sv4 = this.createViewUsage(svPkg, "SV-4_系统功能描述");
        var sig = this.addChild(sv4, this.createActionUsage("声学信号处理"));
        var tma = this.addChild(sv4, this.createActionUsage("目标运动分析"));
        var fc = this.addChild(sv4, this.createActionUsage("火控解算"));
        var df = this.addChild(sv4, this.createActionUsage("数据融合"));

        var sv5a = this.createViewUsage(svPkg, "SV-5a_作战活动-系统功能追溯");

        return emptyResource;
    }

    // --- Core helpers ---

    private org.eclipse.syson.sysml.Element addChild(org.eclipse.syson.sysml.Namespace parent, org.eclipse.syson.sysml.Element child) {
        var m = SysmlFactory.eINSTANCE.createOwningMembership();
        parent.getOwnedRelationship().add(m);
        m.getOwnedRelatedElement().add(child);
        return child;
    }

    private void addToNamespace(org.eclipse.syson.sysml.Namespace ns, org.eclipse.syson.sysml.Element element) {
        this.addChild(ns, element);
    }

    private org.eclipse.syson.sysml.Package createPackage(org.eclipse.syson.sysml.Package parent, String name) {
        var pkg = SysmlFactory.eINSTANCE.createPackage();
        pkg.setDeclaredName(name);
        pkg.setElementId(ElementUtil.generateUUID(pkg).toString());
        if (parent != null) this.addChild(parent, pkg);
        return pkg;
    }

    private org.eclipse.syson.sysml.ViewUsage createViewUsage(org.eclipse.syson.sysml.Namespace parent, String name) {
        var vu = SysmlFactory.eINSTANCE.createViewUsage();
        vu.setDeclaredName(name);
        vu.setElementId(ElementUtil.generateUUID(vu).toString());
        this.addChild(parent, vu);
        return vu;
    }

    private org.eclipse.syson.sysml.PartDefinition createPartDef(String name) {
        var pd = SysmlFactory.eINSTANCE.createPartDefinition();
        pd.setDeclaredName(name);
        pd.setElementId(ElementUtil.generateUUID(pd).toString());
        return pd;
    }

    private org.eclipse.syson.sysml.PartUsage createPartUsage(String name) {
        var pu = SysmlFactory.eINSTANCE.createPartUsage();
        pu.setDeclaredName(name);
        pu.setElementId(ElementUtil.generateUUID(pu).toString());
        return pu;
    }

    private org.eclipse.syson.sysml.ActionUsage createActionUsage(String name) {
        var a = SysmlFactory.eINSTANCE.createActionUsage();
        a.setDeclaredName(name);
        a.setElementId(ElementUtil.generateUUID(a).toString());
        return a;
    }

    private void createMetadataDef(org.eclipse.syson.sysml.Namespace parent, String name) {
        var def = SysmlFactory.eINSTANCE.createMetadataDefinition();
        def.setDeclaredName(name);
        def.setElementId(ElementUtil.generateUUID(def).toString());
        this.addChild(parent, def);
    }

    private void createComment(org.eclipse.syson.sysml.Namespace parent, String name, String body) {
        var c = SysmlFactory.eINSTANCE.createComment();
        c.setDeclaredName(name);
        c.setBody(body);
        c.setElementId(ElementUtil.generateUUID(c).toString());
        this.addChild(parent, c);
    }
}
