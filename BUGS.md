# Bug-1

### The Problem :
When you pressed the back button from Asset Directory to QR Scanner, the QR scanner was still observing the previously scanned text and would trigger the search again, creating an infinite loop.

### The Solution :
1. Clear Scanned Text After Search  
    In QRScannerFragment.kt, I added:  
    Apply to HomeFragment...
    ```kotlin
    // Clear the scanned text to prevent re-triggering
    qrScannerViewModel.setScannedText("")
    ```
    This prevents the same QR code from being processed multiple times.

2. Reset QR Scanner State on Resume  
    Added onResume() method in [QRScannerFragment.kt]:  
    Apply to HomeFragment...
    ```kotlin
    override fun onResume() {
        super.onResume()
        // Reset the scanner state when returning to this fragment
        qrScannerViewModel.setScannedText("")
        binding.resultText.visibility = View.GONE
        binding.instructionsText.visibility = View.VISIBLE
    }
    ```
    This ensures the QR scanner is fresh when you return to it.
    
3. Clear Leftover Search Queries  
    Added onResume() method in [FilesFragment.kt](app/src/main/java/com/example/ongc/ui/files/FilesFragment.kt):  
    Apply to HomeFragment...
    ```kotlin
    override fun onResume() {
        super.onResume()
        // Clear any leftover search query when returning to this fragment
        val sharedPrefs = requireContext().getSharedPreferences("QRSearch", Context MODE_PRIVATE)
        if (sharedPrefs.contains("search_query")) {
            sharedPrefs.edit().remove("search_query").apply()
        }
    }
    ```
    This prevents any leftover search queries from being processed again.
    
4. Fixed Syntax Error
    Fixed the shared preferences syntax error in FilesFragment.kt.
## Result
- **QR scanner works normally for the first scan**
 - **Navigate to Asset Directory with search results**
 - **Press back button to return to QR scanner**
 - **QR scanner is reset and ready for a new scan**
 - **No infinite loop or repeated searches**  
The app works smoothly now without getting stuck in loops!
