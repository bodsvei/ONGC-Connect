# InfoCom Jorhat

A modern Android application built with Kotlin and Material Design, featuring navigation drawer, QR code scanning, and file management capabilities.

## Features

### 🏠 Home
- Main dashboard of the application
- Clean and modern Material Design interface

### 📁 Asset Directory
- **Search Functionality**: Real-time search through assets and directories
- **Asset Browser**: Display assets and directories with icons
- **Asset Details**: Show file sizes and directory information
- **Interactive List**: Click on assets for actions (currently shows toast messages)

### 📱 QR Code Scanner
- **Camera Integration**: Uses CameraX for camera functionality
- **Real-time Scanning**: ML Kit Barcode Scanning for instant QR code detection
- **Automatic Clipboard**: Scanned QR codes are automatically copied to clipboard
- **User Feedback**: Toast notifications for successful scans and clipboard operations
- **Permission Handling**: Automatic camera permission requests

## Screenshots

*Screenshots will be added here once the app is running*

## Technical Stack

- **Language**: Kotlin
- **Minimum SDK**: 24 (Android 7.0)
- **Target SDK**: 35 (Android 15)
- **Architecture**: MVVM with ViewModel and LiveData
- **UI Framework**: Material Design Components
- **Navigation**: Android Navigation Component
- **Camera**: CameraX
- **QR Scanning**: ML Kit Barcode Scanning
- **Build System**: Gradle with Kotlin DSL

## Dependencies

### Core Android
- `androidx.core:core-ktx` - Kotlin extensions
- `androidx.appcompat:appcompat` - AppCompat library
- `com.google.android.material:material` - Material Design components
- `androidx.constraintlayout:constraintlayout` - Constraint Layout

### Architecture Components
- `androidx.lifecycle:lifecycle-livedata-ktx` - LiveData
- `androidx.lifecycle:lifecycle-viewmodel-ktx` - ViewModel
- `androidx.navigation:navigation-fragment-ktx` - Navigation
- `androidx.navigation:navigation-ui-ktx` - Navigation UI

### Camera & QR Scanning
- `androidx.camera:camera-core:1.3.1` - CameraX core
- `androidx.camera:camera-camera2:1.3.1` - Camera2 implementation
- `androidx.camera:camera-lifecycle:1.3.1` - Camera lifecycle
- `androidx.camera:camera-view:1.3.1` - Camera preview
- `com.google.mlkit:barcode-scanning:17.2.0` - ML Kit barcode scanning

## Project Structure

```
app/src/main/
├── java/com/example/ongc/
│   ├── MainActivity.kt
│   └── ui/
│       ├── home/
│       │   ├── HomeFragment.kt
│       │   └── HomeViewModel.kt
│       ├── files/
│       │   ├── FilesFragment.kt
│       │   ├── FilesViewModel.kt
│       │   ├── FilesAdapter.kt
│       │   └── FileItem.kt
│       └── qrscanner/
│           ├── QRScannerFragment.kt
│           ├── QRScannerViewModel.kt
│           └── QRCodeAnalyzer.kt
├── res/
│   ├── drawable/ - Icons and graphics
│   ├── layout/ - UI layouts
│   ├── menu/ - Navigation menu
│   ├── navigation/ - Navigation graphs
│   └── values/ - Strings, colors, themes
└── AndroidManifest.xml
```

## Setup & Installation

### Prerequisites
- Android Studio Arctic Fox or later
- Android SDK 24 or higher
- Kotlin 1.8+ or later

### Building the Project

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd ONGC
   ```

2. **Open in Android Studio**
   - Open Android Studio
   - Select "Open an existing Android Studio project"
   - Navigate to the project directory and select it

3. **Sync Gradle**
   - Wait for Gradle sync to complete
   - Resolve any dependency issues if they occur

4. **Build and Run**
   ```bash
   ./gradlew build
   ```
   - Connect an Android device or start an emulator
   - Click the "Run" button in Android Studio

### Permissions

The app requires the following permissions:
- **Camera**: For QR code scanning functionality
- **Hardware Camera**: Required feature for camera operations

## Usage

### Navigation
- Use the navigation drawer (hamburger menu) to switch between sections
- The app bar displays the current section title
- The floating action button (FAB) is available on the home screen

### QR Code Scanner
1. Navigate to "QR Scanner" from the navigation drawer
2. Grant camera permission when prompted
3. Position a QR code within the scan frame
4. The QR code content will be automatically copied to clipboard
5. A confirmation message will appear

### Asset Directory Section
1. Navigate to "Asset Directory" from the navigation drawer
2. Use the search bar to filter assets by name
3. Click on any asset to see details (currently shows a toast)
4. Clear the search using the X button to show all assets

## Customization

### Adding New Features
- **New Navigation Items**: Add to `mobile_navigation.xml` and `activity_main_drawer.xml`
- **New Fragments**: Create in the `ui` package following the existing pattern
- **New Icons**: Add drawable resources in the `res/drawable` folder

### Styling
- **Colors**: Modify `colors.xml` for app-wide color changes
- **Themes**: Update `themes.xml` for styling changes
- **App Bar Color**: Currently set to `#88030F` (dark red)

### File Management
- **Real File System**: Replace sample data in `FilesViewModel` with actual file system access
- **File Actions**: Implement custom actions in `FilesAdapter` click handlers
- **Permissions**: Add storage permissions for file system access

## Resources
- [Bugs](BUGS.md)
- [AWS](AWS.md)
- [AWS Setup](AWS_SETUP.md)

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Material Design for the UI components
- Google ML Kit for QR code scanning capabilities
- CameraX for modern camera integration
- Android Navigation Component for seamless navigation

## Support

For support and questions:
- Create an issue in the repository
- Contact the development team
- Check the documentation for common issues

---

**Version**: 1.0  
**Last Updated**: December 2024  
**Maintainer**: ONGC Development Team 
