# Generate all DoDAF custom node style files from SysMLImportedPackageNode template
param([switch]$WhatIf)

$nodes = @(
    @{Name="DodafOperationalNode"; Type="customnode:dodafoperationalnode"; GraphQL="DodafOperationalNodeStyle"; Label="作战节点"},
    @{Name="DodafSystemNode"; Type="customnode:dodafsystemnode"; GraphQL="DodafSystemNodeStyle"; Label="系统节点"},
    @{Name="DodafCapability"; Type="customnode:dodafcapability"; GraphQL="DodafCapabilityStyle"; Label="能力节点"},
    @{Name="DodafOrganization"; Type="customnode:dodaforganization"; GraphQL="DodafOrganizationStyle"; Label="组织节点"},
    @{Name="DodafInformationExchange"; Type="customnode:dodafinformationexchange"; GraphQL="DodafInformationExchangeStyle"; Label="信息交换节点"}
)

$baseDir = "E:\syson-rg\syson"
$styleDir = "$baseDir\backend\application\syson-application-configuration\src\main\java\org\eclipse\syson\application\nodes"
$dtoDir = "$styleDir\dto"
$svcDir = "$styleDir\services"
$gqlDir = "$styleDir\graphql"
$feDir = "$baseDir\frontend\syson-components\src\nodes"
$schemaFile = "$baseDir\backend\application\syson-application-configuration\src\main\resources\schema\sysmlcustomnodes.graphqls"

Write-Host "=== Generating DoDAF custom node styles ==="

foreach ($n in $nodes) {
    $name = $n.Name
    $type = $n.Type
    $gql = $n.GraphQL
    Write-Host "  $name ($type)"
}

Write-Host ""
Write-Host "This script needs to generate 20+ files per node type (100+ total)."
Write-Host "Recommended: manually create the first type, then script-copy for the other 4."
Write-Host ""
Write-Host "Files needed per node type:"
Write-Host "  Backend (7): Style, Provider, Deserializer, DTO, Handler, EventHandler, DataFetcher"
Write-Host "  Frontend (8): types, tsx, converter, layout, palette, part, partTypes, hook, hookTypes"
Write-Host "  Config (2): schema.graphqls, AppearanceHandler import"
Write-Host "  Registration (3): NodeTypeRegistry, ExtensionRegistry, DocumentTransform, index.ts"
Write-Host ""
Write-Host "TOTAL: ~20 files per type x 5 types = ~100 files"
Write-Host "USE: Copy SysMLImportedPackageNode* files, replace SysMLImportedPackageNode -> $($nodes[0].Name)"
