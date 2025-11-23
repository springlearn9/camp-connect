# Image Upload Feature for Found Items

## Overview
Image upload functionality has been successfully added to the Found Items feature, allowing users to attach photos when reporting found items.

## New Components Created

### 1. **FileStorageProperties** (`com.ls.common.config.FileStorageProperties`)
Configuration class for file upload settings:
- Upload directory location
- Maximum file size (5MB default)
- Allowed file extensions (jpg, jpeg, png, gif)

### 2. **FileStorageService** (`com.ls.common.service.FileStorageService`)
Service handling file operations:
- `storeFile()` - Store a single file
- `storeFiles()` - Store multiple files
- `loadFile()` - Load a file from storage
- `deleteFile()` - Delete a file
- File validation (size and extension)
- Automatic directory initialization

### 3. **FileUploadController** (`com.ls.common.controller.FileUploadController`)
REST endpoints for file management:
- `POST /api/files/upload` - Upload single file
- `POST /api/files/upload-multiple` - Upload multiple files
- `GET /api/files/download/{fileName}` - Download/view file
- `DELETE /api/files/delete/{fileName}` - Delete file

### 4. **Updated FoundItemController**
New endpoint added:
- `POST /api/campus/found-items/with-images` - Create found item with image uploads

## API Usage

### Creating a Found Item with Images

**Endpoint:** `POST /api/campus/found-items/with-images`

**Content-Type:** `multipart/form-data`

**Request Parameters:**
- `itemName` (required) - Name of the found item
- `description` (optional) - Description of the item
- `location` (optional) - Where the item was found
- `foundDate` (optional) - When the item was found (ISO 8601 format)
- `category` (optional) - Category (ELECTRONICS, DOCUMENTS, CLOTHING, ACCESSORIES, BOOKS, OTHER)
- `contactInfo` (optional) - Contact information
- `distinctiveFeatures` (optional) - Distinctive features
- `handoverLocation` (optional) - Where to handover
- `verificationRequired` (optional) - Boolean
- `isAnonymous` (optional) - Boolean
- `reportedById` (required) - ID of reporting member
- `photo` (optional) - Main photo file
- `additionalImages` (optional) - Array of additional photo files

**Example using cURL:**
```bash
curl -X POST http://localhost:8082/api/campus/found-items/with-images \
  -F "itemName=Blue Backpack" \
  -F "description=Found near library" \
  -F "location=Main Library, 2nd Floor" \
  -F "category=ACCESSORIES" \
  -F "contactInfo=john@example.com" \
  -F "reportedById=1" \
  -F "photo=@/path/to/main-photo.jpg" \
  -F "additionalImages=@/path/to/photo1.jpg" \
  -F "additionalImages=@/path/to/photo2.jpg"
```

**Example using JavaScript (Fetch API):**
```javascript
const formData = new FormData();
formData.append('itemName', 'Blue Backpack');
formData.append('description', 'Found near library');
formData.append('location', 'Main Library, 2nd Floor');
formData.append('category', 'ACCESSORIES');
formData.append('contactInfo', 'john@example.com');
formData.append('reportedById', '1');
formData.append('photo', photoFile); // File object
formData.append('additionalImages', file1);
formData.append('additionalImages', file2);

fetch('http://localhost:8082/api/campus/found-items/with-images', {
  method: 'POST',
  body: formData
})
.then(response => response.json())
.then(data => console.log(data));
```

### Uploading Files Separately

**Upload Single File:**
```bash
curl -X POST http://localhost:8082/api/files/upload \
  -F "file=@/path/to/image.jpg"
```

**Upload Multiple Files:**
```bash
curl -X POST http://localhost:8082/api/files/upload-multiple \
  -F "files=@/path/to/image1.jpg" \
  -F "files=@/path/to/image2.jpg"
```

### Viewing/Downloading Images

**Access uploaded image:**
```
GET http://localhost:8082/api/files/download/{fileName}
```

Example:
```
http://localhost:8082/api/files/download/abc123-xyz789.jpg
```

## Configuration

### application.yml Settings

```yaml
# Multipart file upload settings
spring:
  servlet:
    multipart:
      enabled: true
      max-file-size: 5MB
      max-request-size: 10MB
      file-size-threshold: 2KB

# File storage settings
file:
  upload:
    upload-dir: uploads
    max-file-size: 5242880  # 5MB in bytes
    allowed-extensions: jpg,jpeg,png,gif
```

## File Storage

- Files are stored in the `uploads/` directory (relative to application root)
- Each file gets a unique UUID-based filename to prevent conflicts
- Original file extensions are preserved
- File validation ensures only allowed image types are uploaded

## Response Format

**Success Response (201 Created):**
```json
{
  "id": 1,
  "itemName": "Blue Backpack",
  "description": "Found near library",
  "location": "Main Library, 2nd Floor",
  "foundDate": "2025-11-22T10:30:00",
  "status": "AVAILABLE",
  "category": "ACCESSORIES",
  "contactInfo": "john@example.com",
  "photoUrl": "abc123-xyz789.jpg",
  "additionalImages": "def456-uvw123.jpg,ghi789-rst456.jpg",
  "distinctiveFeatures": null,
  "handoverLocation": null,
  "verificationRequired": true,
  "isAnonymous": false,
  "createdTimestamp": "2025-11-22T10:35:00",
  "reportedById": 1
}
```

## Security Considerations

1. **File Type Validation:** Only allowed extensions (jpg, jpeg, png, gif) can be uploaded
2. **File Size Limit:** Maximum 5MB per file
3. **Path Traversal Protection:** Filenames with ".." are rejected
4. **Unique Filenames:** UUID-based naming prevents filename conflicts and overwrites

## Testing

### Using Postman:
1. Create a new POST request to `/api/campus/found-items/with-images`
2. Select "Body" > "form-data"
3. Add required fields (itemName, reportedById)
4. Add optional fields as needed
5. For file fields (photo, additionalImages), change type to "File" and select image files
6. Send request

### Using Swagger UI:
1. Navigate to `http://localhost:8082/swagger-ui.html`
2. Find the FoundItemController section
3. Expand the POST `/api/campus/found-items/with-images` endpoint
4. Click "Try it out"
5. Fill in the form and upload files
6. Execute the request

## Error Handling

Common errors and their meanings:

- **"Cannot upload empty file"** - No file was selected
- **"File size exceeds maximum allowed size"** - File is larger than 5MB
- **"File type not allowed"** - File extension not in allowed list (jpg, jpeg, png, gif)
- **"Invalid file path"** - Security validation failed (e.g., filename contains "..")
- **"Could not store file"** - I/O error during file storage

## Future Enhancements

Potential improvements:
- Image compression/resizing
- Thumbnail generation
- Cloud storage integration (AWS S3, Azure Blob)
- Image format conversion
- Virus scanning
- CDN integration for faster delivery
