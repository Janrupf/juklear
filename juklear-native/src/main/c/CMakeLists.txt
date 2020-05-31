#################
# Project setup #
#################
cmake_minimum_required(VERSION 3.17)
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
        src/juklear.c
        src/juklear_antialiasing.c
        src/juklear_buffer.c
        src/juklear_constants.c
        src/juklear_context.c
        src/juklear_convert_config.c
        src/juklear_convert_result.c
        src/juklear_draw_command.c
        src/juklear_draw_null_texture.c
        src/juklear_draw_vertex_layout_attribute.c
        src/juklear_draw_vertex_layout_element.c
        src/juklear_draw_vertex_layout_format.c
        src/juklear_font_atlas.c
        src/juklear_font_atlas_editor.c
        src/juklear_font_atlas_format.c
        src/juklear_layouter.c
        src/juklear_nuklear_impl.c
        src/juklear_rect.c
        src/juklear_vec2.c)

##############
# Add target #
##############
add_library(juklear SHARED ${JUKLEAR_SOURCE})
target_link_libraries(juklear PUBLIC ${JNI_LIBRARIES} nuklear)
target_include_directories(juklear PUBLIC ${JUKLEAR_INCLUDE_DIR})
target_include_directories(juklear PRIVATE ${JNI_INCLUDE_DIRS} ${GENERATED_JNI_HEADERS_DIR})

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