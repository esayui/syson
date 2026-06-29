import React, { useState, useCallback, useRef, useEffect } from 'react';
import Draggable from 'react-draggable';

const COLORS = { bg: '#0F172A', nodeBg: '#1E293B', border: '#334155', text: '#E2E8F0', accent: '#3B82F6', red: '#EF4444', blue: '#3B82F6' };

interface Ov1Node { id: string; x: number; y: number; iconId: string; iconUrl: string; label: string; faction: string; }
interface Ov1Edge { id: string; from: string; to: string; }
interface IconDef { id: string; name: string; url: string; }

interface Faction { id: string; label: string; categories: { id: string; label: string; icons: IconDef[] }[]; }

export const Ov1ConceptView: React.FC = () => {
  const [nodes, setNodes] = useState<Ov1Node[]>([]);
  const [edges, setEdges] = useState<Ov1Edge[]>([]);
  const [factions, setFactions] = useState<Faction[]>([]);
  const [selectedFaction, setSelectedFaction] = useState('red');
  const [selectedId, setSelectedId] = useState<string | null>(null);
  const [connectingFrom, setConnectingFrom] = useState<string | null>(null);
  const [edgeMode, setEdgeMode] = useState(false);
  const [label, setLabel] = useState('');
  const canvasRef = useRef<HTMLDivElement>(null);
  const nextId = useRef(1);

  useEffect(() => { fetch('/api/ov1/icons').then(r => r.json()).then(d => setFactions(d.factions || [])).catch(() => {}); }, []);

  const addNode = useCallback((icon: IconDef, faction: string) => {
    const canvas = canvasRef.current;
    const cx = canvas ? canvas.clientWidth / 2 + (Math.random() - 0.5) * 200 : 200;
    const cy = canvas ? canvas.clientHeight / 2 + (Math.random() - 0.5) * 200 : 200;
    const id = 'n' + (nextId.current++);
    setNodes(prev => [...prev, { id, x: cx, y: cy, iconId: icon.id, iconUrl: icon.url, label: label || icon.name, faction }]);
    setLabel('');
  }, [label]);

  const deleteNode = useCallback((id: string) => {
    setNodes(prev => prev.filter(n => n.id !== id));
    setEdges(prev => prev.filter(e => e.from !== id && e.to !== id));
    if (selectedId === id) setSelectedId(null);
  }, [selectedId]);

  const startEdge = useCallback((nodeId: string) => { setConnectingFrom(nodeId); }, []);

  const completeEdge = useCallback((toId: string) => {
    if (connectingFrom && connectingFrom !== toId) {
      setEdges(prev => { if (prev.some(e => e.from === connectingFrom && e.to === toId)) return prev; return [...prev, { id: 'e' + (nextId.current++), from: connectingFrom, to: toId }]; });
    }
    setConnectingFrom(null);
  }, [connectingFrom]);

  const clearCanvas = () => { setNodes([]); setEdges([]); selectedId && setSelectedId(null); };

  const getEdgeLine = (e: Ov1Edge) => {
    const from = nodes.find(n => n.id === e.from);
    const to = nodes.find(n => n.id === e.to);
    if (!from || !to) return null;
    return { x1: from.x + 32, y1: from.y + 32, x2: to.x + 32, y2: to.y + 32 };
  };

  const selectedCategory = factions.find(f => f.id === selectedFaction);
  const edgeStroke = connectingFrom ? COLORS.accent : COLORS.border;

  return (
    <div style={{ display: 'flex', height: '100vh', background: COLORS.bg, color: COLORS.text, fontFamily: 'sans-serif' }}>
      {/* Left Sidebar - Icon Library */}
      <div style={{ width: 240, background: '#1E293B', borderRight: '1px solid #334155', overflowY: 'auto', padding: 12 }}>
        <h3 style={{ fontSize: 14, margin: '0 0 8px', color: COLORS.accent }}>图标库</h3>
        <div style={{ display: 'flex', gap: 4, marginBottom: 12 }}>
          {factions.map(f => (
            <button key={f.id} onClick={() => setSelectedFaction(f.id)}
              style={{ flex: 1, padding: '6px 8px', border: 'none', borderRadius: 4, cursor: 'pointer',
                background: selectedFaction === f.id ? (f.id === 'red' ? COLORS.red : f.id === 'blue' ? COLORS.blue : '#475569') : '#334155',
                color: '#fff', fontSize: 12, fontWeight: selectedFaction === f.id ? 700 : 400 }}>{f.label}</button>
          ))}
        </div>
        {selectedCategory?.categories.map(cat => (
          <div key={cat.id} style={{ marginBottom: 12 }}>
            <div style={{ fontSize: 12, color: '#94A3B8', marginBottom: 4, fontWeight: 600 }}>{cat.label}</div>
            <div style={{ display: 'flex', flexWrap: 'wrap', gap: 4 }}>
              {cat.icons.map(icon => (
                <div key={icon.id} onClick={() => addNode(icon, selectedFaction)}
                  style={{ width: 48, height: 48, background: COLORS.nodeBg, border: '1px solid #334155', borderRadius: 6,
                    display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center', cursor: 'pointer', padding: 4 }}>
                  <img src={icon.url} alt={icon.name} style={{ width: 24, height: 24 }} />
                  <span style={{ fontSize: 9, marginTop: 2, overflow: 'hidden', textOverflow: 'ellipsis', whiteSpace: 'nowrap', maxWidth: 44 }}>{icon.name}</span>
                </div>
              ))}
            </div>
          </div>
        ))}
        {/* Add node label input */}
        <div style={{ marginTop: 12 }}>
          <input value={label} onChange={e => setLabel(e.target.value)} placeholder="节点名称(可选)"
            style={{ width: '100%', padding: '6px 8px', fontSize: 12, borderRadius: 4, border: '1px solid #334155', background: COLORS.bg, color: COLORS.text }} />
        </div>
        {/* Mode buttons */}
        <div style={{ marginTop: 12, display: 'flex', gap: 6 }}>
          <button onClick={() => { setEdgeMode(!edgeMode); setConnectingFrom(null); }}
            style={{ flex: 1, padding: 8, border: 'none', borderRadius: 4, cursor: 'pointer',
              background: edgeMode ? COLORS.accent : '#334155', color: '#fff', fontSize: 12 }}>{edgeMode ? '连线模式 ON' : '添加节点'}</button>
          <button onClick={clearCanvas} style={{ padding: 8, border: 'none', borderRadius: 4, cursor: 'pointer', background: COLORS.red, color: '#fff', fontSize: 12 }}>清空</button>
        </div>
        {selectedId && (
          <div style={{ marginTop: 8, fontSize: 11, color: '#94A3B8' }}>
            选中节点: {selectedId} &nbsp;
            <button onClick={() => deleteNode(selectedId)} style={{ background: 'transparent', color: COLORS.red, border: 'none', cursor: 'pointer', fontSize: 11 }}>删除</button>
            <button onClick={() => { setConnectingFrom(null); setEdgeMode(false); setSelectedId(null); }} style={{ background: 'transparent', color: '#94A3B8', border: 'none', cursor: 'pointer', fontSize: 11, marginLeft: 4 }}>取消</button>
          </div>
        )}
      </div>

      {/* Canvas */}
      <div ref={canvasRef} style={{ flex: 1, position: 'relative', overflow: 'hidden', background: 'radial-gradient(circle, #1E293B 0%, #0F172A 100%)' }}
        onClick={() => { setSelectedId(null); setConnectingFrom(null); }}>
        {/* Edge SVG layer */}
        <svg style={{ position: 'absolute', top: 0, left: 0, width: '100%', height: '100%', pointerEvents: 'none' }}>
          {edges.map(e => { const line = getEdgeLine(e); return line ? (
            <g key={e.id}>
              <line {...line} stroke={edgeStroke} strokeWidth={2} />
              <polygon points={`${line.x2 - 6},${line.y2 - 4} ${line.x2},${line.y2} ${line.x2 - 6},${line.y2 + 4}`}
                fill={edgeStroke} transform={`rotate(${Math.atan2(line.y2 - line.y1, line.x2 - line.x1) * 180 / Math.PI}, ${line.x2}, ${line.y2})`} />
            </g>
          ) : null; })}
        </svg>
        {/* Connecting line */}
        {connectingFrom && (() => { const fn = nodes.find(n => n.id === connectingFrom); if (!fn) return null;
          return <svg style={{ position: 'absolute', top: 0, left: 0, width: '100%', height: '100%', pointerEvents: 'none' }}>
            <line x1={fn.x + 32} y1={fn.y + 32} x2={fn.x + 132} y2={fn.y + 32} stroke={COLORS.accent} strokeWidth={1.5} strokeDasharray="4,3" />
          </svg>; })()}
        {/* Nodes */}
        {nodes.map(node => (
          <Draggable key={node.id} position={{ x: node.x, y: node.y }} onStop={(_, d) => setNodes(prev => prev.map(n => n.id === node.id ? { ...n, x: d.x, y: d.y } : n))}>
            <div onClick={e => { e.stopPropagation(); if (edgeMode) completeEdge(node.id); else setSelectedId(node.id); }}
              onContextMenu={e => { e.preventDefault(); deleteNode(node.id); }}
              style={{ position: 'absolute', width: 64, height: 84, cursor: edgeMode ? 'crosshair' : 'pointer',
                background: selectedId === node.id ? 'rgba(59,130,246,0.2)' : 'transparent', borderRadius: 8,
                border: selectedId === node.id ? '2px solid #3B82F6' : (connectingFrom === node.id ? '2px solid #3B82F6' : '1px solid transparent'),
                display: 'flex', flexDirection: 'column', alignItems: 'center', padding: 4 }}>
              <img src={node.iconUrl} alt="" style={{ width: 40, height: 40 }} />
              <span style={{ fontSize: 9, color: COLORS.text, textAlign: 'center', marginTop: 2, maxWidth: 60, overflow: 'hidden', textOverflow: 'ellipsis', whiteSpace: 'nowrap' }}>{node.label}</span>
            </div>
          </Draggable>
        ))}
        {nodes.length === 0 && (
          <div style={{ position: 'absolute', top: '50%', left: '50%', transform: 'translate(-50%,-50%)', color: '#475569', fontSize: 14 }}>
            点击左侧图标库添加作战概念节点
          </div>
        )}
      </div>
    </div>
  );
};
