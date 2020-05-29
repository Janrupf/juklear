#################################
# Add nuklear interface library #
#################################
add_library(nuklear INTERFACE)
target_include_directories(nuklear INTERFACE "${CMAKE_CURRENT_LIST_DIR}/Nuklear")
target_sources(nuklear INTERFACE "${CMAKE_CURRENT_LIST_DIR}/Nuklear/nuklear.h")
