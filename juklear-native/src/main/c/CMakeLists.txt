#####################
# Pre project setup #
#####################
set(CMAKE_OSX_DEPLOYMENT_TARGET "10.10" CACHE STRING "Minimum OS X deployment version" FORCE)

#################
# Project setup #
#################
cmake_minimum_required(VERSION 3.15)
project(juklear C)

#####################
# Set CMake options #
#####################
set(CMAKE_C_STANDARD 11)

################################
# Set up 3rdparty dependencies #
################################
add_subdirectory(3rdparty)

#####################
# Find dependencies #
#####################
find_package(JNI REQUIRED)

#####################
# Configure project #
#####################
if(NOT DEFINED GENERATED_JNI_HEADERS_DIR)
    message(FATAL_ERROR "Please run cmake with -DGENERATED_JNI_HEADERS_DIR=/path/to/generated/headers or simply use "
            "the gradle build task")
endif()

##########
# Source #
##########
set(JUKLEAR_INCLUDE_DIR
        "${CMAKE_CURRENT_LIST_DIR}/include")

set(JUKLEAR_SOURCE
        src/drawing/juklear_antialiasing.c
        src/drawing/juklear_convert_config.c
        src/drawing/juklear_convert_result.c
        src/drawing/juklear_draw_command.c
        src/drawing/juklear_draw_null_texture.c
        src/drawing/juklear_draw_vertex_layout_attribute.c
        src/drawing/juklear_draw_vertex_layout_element.c
        src/drawing/juklear_draw_vertex_layout_format.c

        src/font/juklear_font.c
        src/font/juklear_font_atlas.c
        src/font/juklear_font_atlas_editor.c
        src/font/juklear_font_atlas_format.c

        src/image/juklear_java_image.c

        src/input/juklear_mouse_button.c
        src/input/juklear_input.c
        src/input/juklear_key.c

        src/layout/component/row/template/juklear_templated_row.c
        src/layout/component/row/template/juklear_templated_row_dynamic.c
        src/layout/component/row/template/juklear_templated_row_static.c
        src/layout/component/row/template/juklear_templated_row_variable.c

        src/layout/component/row/juklear_dynamic_row.c
        src/layout/component/row/juklear_static_row.c

        src/layout/component/juklear_button.c
        src/layout/component/juklear_group.c
        src/layout/component/juklear_image_display.c
        src/layout/component/juklear_label.c
        src/layout/component/juklear_spacing.c
        src/layout/component/juklear_window.c

        src/layout/juklear_text_alignment_flags.c

        src/math/juklear_rect.c
        src/math/juklear_vec2.c

        src/style/item/juklear_style_item.c
        src/style/item/juklear_style_item_type.c

        src/style/primitive/juklear_style_enum.c
        src/style/primitive/juklear_style_flags.c
        src/style/primitive/juklear_style_float.c

        src/style/juklear_button_style.c
        src/style/juklear_color.c
        src/style/juklear_header_alignment.c
        src/style/juklear_style.c
        src/style/juklear_symbol_type.c
        src/style/juklear_text_style.c
        src/style/juklear_window_header_style.c
        src/style/juklear_window_style.c

        src/util/juklear_buffer.c
        src/util/juklear_constants.c

        src/juklear.c
        src/juklear_context.c
        src/juklear_nuklear_impl.c
        )

##############
# Add target #
##############
add_library(juklear SHARED ${JUKLEAR_SOURCE})
target_link_libraries(juklear PUBLIC ${JNI_LIBRARIES} nuklear)
target_include_directories(juklear PUBLIC ${JUKLEAR_INCLUDE_DIR})
target_include_directories(juklear PRIVATE ${JNI_INCLUDE_DIRS} ${GENERATED_JNI_HEADERS_DIR})
target_compile_definitions(juklear PUBLIC NK_BUTTON_TRIGGER_ON_RELEASE=1)

#######################
# Set compile options #
#######################
if(NOT MSVC)
    target_compile_options(juklear PRIVATE -fPIC -fstack-protector)
else()
    set_property(
            TARGET juklear
            PROPERTY MSVC_RUNTIME_LIBRARY "MultiThreadedDebug"
    )
endif()
