#pragma once

#define NK_INCLUDE_FONT_BAKING
#define NK_INCLUDE_DEFAULT_ALLOCATOR
#define NK_INCLUDE_STANDARD_IO
#define NK_INCLUDE_DEFAULT_FONT
#define NK_INCLUDE_VERTEX_BUFFER_OUTPUT
#include "nuklear.h"

typedef struct nk_context nk_context_t;
typedef struct nk_font_atlas nk_font_atlas_t;
typedef struct nk_font nk_font_t;
typedef struct nk_buffer nk_buffer_t;
typedef struct nk_vec2 nk_vec2_t;
typedef struct nk_draw_null_texture nk_draw_null_texture_t;
typedef struct nk_convert_config nk_convert_config_t;
typedef struct nk_draw_vertex_layout_element nk_draw_vertex_layout_element_t;
typedef struct nk_draw_command nk_draw_command_t;
typedef struct nk_rect nk_rect_t;
