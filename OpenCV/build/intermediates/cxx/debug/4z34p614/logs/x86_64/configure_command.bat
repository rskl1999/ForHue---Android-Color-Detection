@echo off
"D:\\New Folder\\cmake\\3.18.1\\bin\\cmake.exe" ^
  "-HD:\\ForHuefirst\\OpenCV\\libcxx_helper" ^
  "-DCMAKE_SYSTEM_NAME=Android" ^
  "-DCMAKE_EXPORT_COMPILE_COMMANDS=ON" ^
  "-DCMAKE_SYSTEM_VERSION=21" ^
  "-DANDROID_PLATFORM=android-21" ^
  "-DANDROID_ABI=x86_64" ^
  "-DCMAKE_ANDROID_ARCH_ABI=x86_64" ^
  "-DANDROID_NDK=D:\\New Folder\\ndk\\23.1.7779620" ^
  "-DCMAKE_ANDROID_NDK=D:\\New Folder\\ndk\\23.1.7779620" ^
  "-DCMAKE_TOOLCHAIN_FILE=D:\\New Folder\\ndk\\23.1.7779620\\build\\cmake\\android.toolchain.cmake" ^
  "-DCMAKE_MAKE_PROGRAM=D:\\New Folder\\cmake\\3.18.1\\bin\\ninja.exe" ^
  "-DCMAKE_LIBRARY_OUTPUT_DIRECTORY=D:\\ForHuefirst\\OpenCV\\build\\intermediates\\cxx\\Debug\\4z34p614\\obj\\x86_64" ^
  "-DCMAKE_RUNTIME_OUTPUT_DIRECTORY=D:\\ForHuefirst\\OpenCV\\build\\intermediates\\cxx\\Debug\\4z34p614\\obj\\x86_64" ^
  "-DCMAKE_BUILD_TYPE=Debug" ^
  "-BD:\\ForHuefirst\\OpenCV\\.cxx\\Debug\\4z34p614\\x86_64" ^
  -GNinja ^
  "-DANDROID_STL=c++_shared"
