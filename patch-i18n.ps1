$ErrorActionPreference = "Stop"
$dist = "E:\syson-rg\syson\node_modules\@eclipse-sirius\sirius-web-application\dist"
$viteCache = "E:\syson-rg\syson\frontend\syson\node_modules\.vite"
$frontend = "E:\syson-rg\syson\frontend\syson"

$replacements = @(
    @('title: "Explorer",',    'title: "资源管理器",'),
    @('title: "Explorers",',   'title: "资源管理器",'),
    @('title: "Views",',       'title: "视图",'),
    @('title: "Validation",',  'title: "验证",'),
    @('title: "Search",',      'title: "搜索",'),
    @('title: "Details",',     'title: "详情",'),
    @('title: "Query",',       'title: "查询",'),
    @('title: "Related Views",','title: "关联视图",'),
    @('title: "Related Elements",','title: "关联元素",'),
    @('title: "General",',     'title: "通用",'),
    @('title: "Images",',      'title: "图片",'),
    @('title: "Publish Studios",','title: "发布工作室",'),
    @('title: "Import libraries",','title: "导入库",'),
    @('title: "Select the container"','title: "选择容器"'),
    @('"aria-label": "Explorers"','"aria-label": "资源管理器"')
)

foreach ($file in @("sirius-web-application.es.js", "sirius-web-application.umd.js")) {
    $path = Join-Path $dist $file
    Write-Host "Patching: $file"
    $content = [IO.File]::ReadAllText($path)
    foreach ($r in $replacements) {
        $content = $content.Replace($r[0], $r[1])
    }
    [IO.File]::WriteAllText($path, $content)
}

Write-Host "Clearing Vite cache..."
if (Test-Path $viteCache) { Remove-Item -Recurse -Force $viteCache }

Write-Host "Killing node processes..."
Get-Process -Name "node" -ErrorAction SilentlyContinue | Stop-Process -Force

Start-Sleep 2

Write-Host "Starting Vite..."
Set-Location $frontend
Start-Process -FilePath "npx" -ArgumentList "vite","--host"

Write-Host "Done! Refresh browser with Ctrl+Shift+R"
Read-Host "Press Enter to exit"
