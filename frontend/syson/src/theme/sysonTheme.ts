/*******************************************************************************
 * Copyright (c) 2023, 2026 Obeo.
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

import { theme } from '@eclipse-sirius/sirius-components-core';
import { Theme, createTheme } from '@mui/material/styles';

export const baseTheme: Theme = createTheme({
  ...theme,
  palette: {
    mode: 'dark',
    primary: {
      main: '#00D4FF',
      dark: '#0098B8',
      light: '#66E5FF',
    },
    secondary: {
      main: '#7C3AED',
      dark: '#5B21B6',
      light: '#A78BFA',
    },
    text: {
      primary: '#E2E8F0',
      secondary: '#94A3B8',
      disabled: '#475569',
    },
    error: {
      main: '#EF4444',
      dark: '#B91C1C',
      light: '#F87171',
    },
    success: {
      main: '#10B981',
      dark: '#059669',
      light: '#34D399',
    },
    warning: {
      main: '#F59E0B',
      dark: '#D97706',
      light: '#FBBF24',
    },
    info: {
      main: '#00D4FF',
      dark: '#0098B8',
      light: '#66E5FF',
    },
    divider: '#1E293B',
    background: {
      default: '#0B1121',
      paper: '#111827',
    },
    navigation: {
      leftBackground: '#0F172A',
      rightBackground: '#0B1121',
    },
    navigationBar: {
      border: 'rgba(0, 212, 255, 0.3)',
      background: '#020617',
    },
    selected: '#00D4FF',
    action: {
      hover: 'rgba(0, 212, 255, 0.08)',
      selected: 'rgba(0, 212, 255, 0.16)',
      disabledBackground: 'rgba(255, 255, 255, 0.04)',
    },
  },
  typography: {
    fontFamily: "'Lato', 'Helvetica Neue', Helvetica, Arial, sans-serif",
    fontSize: 14,
    fontWeightLight: 300,
    fontWeightRegular: 400,
    fontWeightMedium: 600,
    fontWeightBold: 700,
    h1: { fontWeight: 700, fontSize: '2rem', lineHeight: 1.2, letterSpacing: '-0.02em' },
    h2: { fontWeight: 700, fontSize: '1.5rem', lineHeight: 1.3, letterSpacing: '-0.01em' },
    h3: { fontWeight: 600, fontSize: '1.25rem', lineHeight: 1.4 },
    h4: { fontWeight: 600, fontSize: '1.125rem', lineHeight: 1.4 },
    h5: { fontWeight: 600, fontSize: '1rem', lineHeight: 1.5 },
    h6: { fontWeight: 600, fontSize: '0.875rem', lineHeight: 1.5 },
    body1: { fontSize: '0.875rem', lineHeight: 1.6 },
    body2: { fontSize: '0.8125rem', lineHeight: 1.6 },
    caption: { fontSize: '0.75rem', lineHeight: 1.5 },
  },
  shape: {
    borderRadius: 8,
  },
  components: {
    MuiSnackbarContent: {
      styleOverrides: {
        root: {
          backgroundColor: '#111827',
          borderRadius: 8,
          border: '1px solid #1E293B',
        },
      },
    },
    MuiButton: {
      styleOverrides: {
        root: {
          textTransform: 'none',
          fontWeight: 600,
          borderRadius: 8,
        },
        containedPrimary: {
          background: 'linear-gradient(135deg, #00D4FF 0%, #0098B8 100%)',
          '&:hover': {
            background: 'linear-gradient(135deg, #66E5FF 0%, #00D4FF 100%)',
          },
        },
      },
    },
    MuiPaper: {
      styleOverrides: {
        root: {
          backgroundImage: 'none',
        },
        outlined: {
          borderColor: 'rgba(0, 212, 255, 0.12)',
        },
      },
    },
    MuiCard: {
      styleOverrides: {
        root: {
          border: '1px solid rgba(0, 212, 255, 0.1)',
          '&:hover': {
            borderColor: 'rgba(0, 212, 255, 0.25)',
          },
        },
      },
    },
    MuiAppBar: {
      colorPrimary: 'secondary',
      styleOverrides: {
        root: {
          borderBottom: '1px solid rgba(0, 212, 255, 0.15)',
        },
      },
    },
    MuiChip: {
      styleOverrides: {
        root: {
          borderRadius: 8,
        },
      },
    },
    MuiToolbar: {
      styleOverrides: {
        root: {
          backgroundColor: 'rgba(2, 6, 23, 0.8)',
        },
      },
    },
  },
});

const container = () => {
  return document.fullscreenElement ?? document.body;
};

export const sysonTheme = createTheme(
  {
    components: {
      MuiAvatar: {
        styleOverrides: {
          colorDefault: {
            backgroundColor: baseTheme.palette.primary.main,
            color: '#020617',
          },
        },
      },
      MuiMenu: {
        defaultProps: {
          container,
        },
        styleOverrides: {
          paper: {
            backgroundColor: '#111827',
            border: '1px solid #1E293B',
            boxShadow: '0 4px 24px rgba(0, 0, 0, 0.4)',
          },
          list: {
            padding: '4px',
          },
        },
      },
      MuiMenuItem: {
        styleOverrides: {
          root: {
            borderRadius: 6,
            margin: '2px 0',
            padding: '8px 14px',
            fontSize: '0.8125rem',
            '&:hover': {
              backgroundColor: 'rgba(0, 212, 255, 0.08)',
            },
            '&.Mui-selected': {
              backgroundColor: 'rgba(0, 212, 255, 0.16)',
            },
          },
        },
      },
      MuiTooltip: {
        defaultProps: {
          PopperProps: {
            container,
          },
        },
        styleOverrides: {
          tooltip: {
            backgroundColor: '#1E293B',
            color: '#E2E8F0',
            fontSize: '0.75rem',
            padding: '6px 10px',
            borderRadius: 6,
            border: '1px solid #334155',
          },
        },
      },
      MuiInputBase: {
        styleOverrides: {
          root: {
            fontSize: '0.8125rem',
            '& fieldset': {
              borderColor: '#1E293B',
            },
          },
        },
      },
      MuiOutlinedInput: {
        styleOverrides: {
          root: {
            '&:hover .MuiOutlinedInput-notchedOutline': {
              borderColor: '#00D4FF',
            },
            '&.Mui-focused .MuiOutlinedInput-notchedOutline': {
              borderColor: '#00D4FF',
            },
          },
        },
      },
      MuiTab: {
        styleOverrides: {
          root: {
            textTransform: 'none',
            fontWeight: 600,
            fontSize: '0.8125rem',
          },
        },
      },
      MuiListItemButton: {
        styleOverrides: {
          root: {
            borderRadius: 6,
            margin: '1px 4px',
            '&:hover': {
              backgroundColor: 'rgba(0, 212, 255, 0.06)',
            },
            '&.Mui-selected': {
              backgroundColor: 'rgba(0, 212, 255, 0.12)',
              borderLeft: '3px solid #00D4FF',
            },
          },
        },
      },
      MuiListItemIcon: {
        styleOverrides: {
          root: {
            minWidth: 36,
            color: '#94A3B8',
          },
        },
      },
      MuiDivider: {
        styleOverrides: {
          root: {
            borderColor: '#1E293B',
          },
        },
      },
      ['MuiTreeItem' as any]: {
        styleOverrides: {
          root: {
            '& .MuiTreeItem-content:hover': {
              backgroundColor: 'rgba(0, 212, 255, 0.06)',
            },
            '& .MuiTreeItem-content.Mui-selected': {
              backgroundColor: 'rgba(0, 212, 255, 0.12)',
            },
          },
        },
      },
      MuiPaper: {
        styleOverrides: {
          elevation2: {
            backgroundColor: 'rgba(17, 24, 39, 0.8) !important',
            '& *': {
              backgroundColor: 'transparent !important',
            },
          },
        },
      },
      MuiButton: {
        styleOverrides: {
          textPrimary: {
            backgroundColor: 'rgba(17, 24, 39, 0.8) !important',
            '&:hover': {
              backgroundColor: 'rgba(17, 24, 39, 0.9) !important',
            },
            '& *': {
              backgroundColor: 'transparent !important',
            },
          },
        },
      },
      MuiToolbar: {
        styleOverrides: {
          root: {
            '& .MuiButton-root, & .MuiIconButton-root, & .MuiTypography-root': {
              backgroundColor: 'transparent !important',
            },
          },
        },
      },
    },
  },
  baseTheme
);
