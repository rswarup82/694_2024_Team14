# Using lightweight alpine image
FROM python:3.8-alpine

# Installing packages
RUN apk update
RUN pip install --no-cache-dir pipenv

# Defining working directory and adding source code
WORKDIR /usr/src/searchapp
COPY Pipfile Pipfile.lock bootstrap.sh ./
COPY searchapp ./searchapp

# Install API dependencies
RUN pipenv install --system --deploy

# Start app
EXPOSE 8001
ENTRYPOINT ["/usr/src/searchapp/bootstrap.sh"]