# AWS Setup Guide for ONGC File Management App

This guide will help you set up the AWS services required for the file management functionality.

## Prerequisites

1. AWS Account
2. AWS CLI installed and configured
3. Basic knowledge of AWS services

## Required AWS Services

1. **Amazon Cognito** - For user authentication and identity management
2. **Amazon DynamoDB** - For storing file metadata
3. **Amazon S3** - For storing actual files
4. **IAM** - For managing permissions

## Step-by-Step Setup

### 1. Create DynamoDB Table

Create a DynamoDB table named `ONGC_Files` with the following structure:

```bash
aws dynamodb create-table \
    --table-name ONGC_Files \
    --attribute-definitions AttributeName=id,AttributeType=S \
    --key-schema AttributeName=id,KeyType=HASH \
    --billing-mode PAY_PER_REQUEST \
    --region us-east-1
```

### 2. Create S3 Bucket

Create an S3 bucket for storing files:

```bash
aws s3 mb s3://ongc-files-bucket --region us-east-1
```

### 3. Create Cognito Identity Pool

1. Go to AWS Cognito Console
2. Create a new Identity Pool
3. Configure authentication providers
4. Note down the Identity Pool ID

### 4. Update App Configuration

1. Update `AWSFileService.kt` with your Identity Pool ID
2. Update `awsconfiguration.json` with your AWS settings

## Security Considerations

1. Use least privilege IAM policies
2. Enable encryption for S3 and DynamoDB
3. Configure proper CORS settings for S3
4. Use strong authentication policies

## Testing

1. Build and run the app
2. Test file upload, download, and delete operations
3. Verify data appears in AWS services
4. Check error handling and edge cases 