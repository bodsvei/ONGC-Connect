# Connecting Server Stats to AWS

This guide explains how to securely connect your Android app's "Server Stats" section to your actual AWS infrastructure, following best practices for security and maintainability.

---

## 1. Why Not Direct AWS Access from Mobile?
- **Never embed AWS credentials in your app.**
- Direct access exposes your AWS account to security risks.
- Always use a backend as a proxy between your app and AWS.

---

## 2. Recommended Architecture

```
[Android App] ⇄ [Your Backend API] ⇄ [AWS Services]
```

- The app talks to your backend via HTTPS.
- The backend (Node.js, Python, etc.) uses AWS SDKs to fetch server stats.
- The backend returns only the necessary data to the app.

---

## 3. Backend API Example (Node.js/Express)

**Install dependencies:**
```bash
npm install express aws-sdk
```

**Sample Route:**
```js
const express = require('express');
const AWS = require('aws-sdk');
const router = express.Router();

AWS.config.update({ region: 'us-east-1' }); // Set your region

const ec2 = new AWS.EC2();
const cloudwatch = new AWS.CloudWatch();

router.get('/server-stats', async (req, res) => {
  try {
    // List EC2 instances
    const instances = await ec2.describeInstances().promise();
    // For each instance, fetch CloudWatch metrics (CPU, etc.)
    // ... (fetch and format data)
    res.json({ /* formatted server stats */ });
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

module.exports = router;
```

- Deploy this backend (Heroku, AWS Lambda/API Gateway, EC2, etc.).
- Secure it with authentication (API keys, JWT, etc.).

---

## 4. IAM Permissions
- The backend should use an IAM user/role with only the permissions needed:
  - `ec2:DescribeInstances`
  - `cloudwatch:GetMetricData`
- Never use root credentials.
- Rotate keys regularly.

---

## 5. Android App Integration

- Use Retrofit, OkHttp, or similar to call your backend API.
- Parse the JSON response and update your `ServerStatsViewModel` with real data.

**Retrofit Example:**
```kotlin
interface ServerStatsApi {
    @GET("server-stats")
    suspend fun getServerStats(): List<ServerStat>
}
```
- Replace the `loadServerStats()` method in your `ServerStatsViewModel` to call this API.

---

## 6. Security Best Practices
- **Never embed AWS secret keys in your Android app.**
- Always use HTTPS for all API calls.
- Authenticate and authorize API requests (API keys, JWT, OAuth, etc.).
- Use least-privilege IAM roles for your backend.
- Monitor and log API usage.

---

## 7. Useful AWS Services
- **EC2**: For instance details
- **CloudWatch**: For metrics (CPU, memory, network, etc.)
- **IAM**: For secure access control
- **API Gateway + Lambda**: For serverless backend APIs

---

## 8. References
- [AWS SDK for Node.js](https://docs.aws.amazon.com/sdk-for-javascript/v2/developer-guide/welcome.html)
- [AWS EC2 API Docs](https://docs.aws.amazon.com/AWSEC2/latest/APIReference/Welcome.html)
- [AWS CloudWatch API Docs](https://docs.aws.amazon.com/AmazonCloudWatch/latest/APIReference/Welcome.html)
- [Retrofit Android Guide](https://square.github.io/retrofit/)

---

## 9. Summary Table

| Step                | What to Do                                      | Why?                        |
|---------------------|-------------------------------------------------|-----------------------------|
| 1. Backend API      | Build a backend to fetch AWS stats              | Security, flexibility       |
| 2. Android Fetch    | Call backend API from your app                  | No secrets in app           |
| 3. IAM Permissions  | Use least-privilege AWS credentials on backend  | Security best practice      |
| 4. Secure Comm      | Use HTTPS, authenticate API                     | Protect data in transit     |

---

## 10. Need Help?
- If you need a sample backend or Android Retrofit code, ask your developer or request a template.
- For more details, consult the AWS documentation or your cloud administrator. 