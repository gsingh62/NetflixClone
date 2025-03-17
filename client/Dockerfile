# Use official Node.js image as base
FROM node:18-alpine

# Set working directory inside the container
WORKDIR /app

# Set Node.js user for correct permissions
RUN chown -R node:node /app

# Switch to non-root user
USER node

# Copy package.json and install dependencies
COPY --chown=node:node package.json package-lock.json ./

# Install dependencies
RUN npm install

# Copy the entire frontend project
COPY --chown=node:node . .

# Expose port 3000
EXPOSE 3000

# Start the React application
CMD ["npm", "start"]
