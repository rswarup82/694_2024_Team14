#!/bin/sh
export FLASK_APP=./searchapp/index.py
pipenv run flask --debug run -h 0.0.0.0