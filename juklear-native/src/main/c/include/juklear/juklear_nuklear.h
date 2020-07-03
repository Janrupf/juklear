#pragma once

#define NK_SIN juklear_sin
#define NK_COS juklear_cos

float juklear_sin(float x);
float juklear_cos(float x);

#define NK_INCLUDE_FONT_BAKING
#define NK_INCLUDE_DEFAULT_ALLOCATOR
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
typedef struct nk_image nk_image_t;
typedef struct nk_color nk_color_t;
typedef struct nk_style nk_style_t;
typedef struct nk_style_text nk_style_text_t;
typedef struct nk_style_item nk_style_item_t;
typedef struct nk_style_button nk_style_button_t;
typedef struct nk_style_window_header nk_style_window_header_t;
typedef struct nk_style_window nk_style_window_t;
typedef struct nk_user_font nk_user_font_t;