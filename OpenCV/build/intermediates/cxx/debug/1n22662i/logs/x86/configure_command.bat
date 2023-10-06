@echo off
"D:\\New Folder\\cmake\\3.18.1\\bin\\cmake.exe" ^
  "-HE:\\graylaptop\\ForHuefirst\\OpenCV\\libcxx_helper" ^
  "-DCMAKE_SYSTEM_NAME=Android" ^
  "-DCMAKE_EXPORT_COMPILE_COMMANDS=ON" ^
  "-DCMAKE_SYSTEM_VERSION=21" ^
  "-DANDROID_PLATFORM=android-21" ^
  "-DANDROID_ABI=x86" ^
  "-DCMAKE_ANDROID_ARCH_ABI=x86" ^
  "-DANDROID_NDK=D:\\New Folder\\ndk\\23.1.7779620" ^
  "-DCMAKE_ANDROID_NDK=D:\\New Folder\\ndk\\23.1.7779620" ^
  "-DCMAKE_TOOLCHAIN_FILE=D:\\New Folder\\ndk\\23.1.7779620\\build\\cmake\\android.toolchain.cmake" ^
  "-DCMAKE_MAKE_PROGRAM=D:\\New Folder\\cmake\\3.18.1\\bin\\ninja.exe" ^
  "-DCMAKE_LIBRARY_OUTPUT_DIRECTORY=E:\\graylaptop\\ForHuefirst\\OpenCV\\build\\intermediates\\cxx\\Debug\\1n22662i\\obj\\x86" ^
  "-DCMAKE_RUNTIME_OUTPUT_DIRECTORY=E:\\graylaptop\\ForHuefirst\\OpenCV\\build\\intermediates\\cxx\\Debug\\1n22662i\\obj\\x86" ^
  "-DCMAKE_BUILD_TYPE=Debug" ^
  "-BE:\\graylaptop\\ForHuefirst\\OpenCV\\.cxx\\Debug\\1n22662i\\x86" ^
  -GNinja ^
  "-DANDROID_STL=c++_shared"
