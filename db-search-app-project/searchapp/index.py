import requests
from cachelib import redis
from flask import Flask, jsonify, request, render_template, redirect, url_for
from pymongo import MongoClient
from flask_caching import Cache
import logging

import redis
#from flask_redis import FlaskRedis
from bson import ObjectId

from searchapp.model.expense import Expense, ExpenseSchema
from searchapp.model.income import Income, IncomeSchema
from searchapp.model.transaction_type import TransactionType

from searchapp.model.identity import Identity, IdentitySchema

from searchapp.db.users_db import insert_into_identity_tbl, find_from_identity_tbl
from searchapp.db.tweet_db import insert_into_identity_collection, find_from_identity_collection

app = Flask(__name__)

cache = Cache(app, config={
    "CACHE_TYPE": "redis",
    "CACHE_REDIS_HOST": "redis",
    "CACHE_REDIS_PORT": 6379,
    "CACHE_REDIS_DB": 0,
    "CACHE_REDIS_URL": "redis://redis:6379/0",
    "CACHE_DEFAULT_TIMEOUT": 300
})

#app.config['REDIS_URL'] = 'redis://localhost:6379/0'
#redis_store = FlaskRedis(app)

client = MongoClient(host='mongodb_final_project_cluster', port=27017, username='root', password='pass', authSource="admin")
db = client.tweet_db
tweets_collection = db.tweets

#redis = Redis(host='redis', port=6379)

#incomes = [
#    { 'description' : 'salary', 'amount' : 5000 }
#]

transactions = [
    Income('Salary', 5000),
    Income('Dividends', 200),
    Expense('pizza', 50),
    Expense('Rock Concert', 100)
]

@app.route("/")
def hello_world():
    identity = Identity("hello", "world")
    insert_into_identity_tbl(identity)
    return "success", 200

@app.route("/identity", methods=['POST'])
def add_identity():
    identity = IdentitySchema().load(request.get_json())
    app.logger.info(jsonify(identity))
    insert_into_identity_tbl(identity)
    return "", 200

@app.route("/identity")
def search_identity():
    search = request.args.get('_name')
    identity = find_from_identity_tbl(search)
    return jsonify(identity), 200

@app.route("/universities")
@cache.cached(timeout=300, query_string=True)
def get_universities():
    API_URL = "http://universities.hipolabs.com/search?country="
    search = request.args.get('country')

    # check of the response is already cached
    cached_response = cache.get(search)
    if cached_response:
        print('return data from cached_response !!!')
        return jsonify(cached_response)

    # get it from external source
    r = requests.get(f"{API_URL}{search}")

    return jsonify(r.json())

@app.route("/identity-mongo", methods=['POST'])
def add_identity_to_mongodb():
    print("add identity to mongodb...")
    identity = IdentitySchema().load(request.get_json())
    insert_into_identity_collection(identity)
    return "", 200

@app.route("/identity-mongo")
def search_identity_from_mongo():
    print("add identity from mongodb...")
    search = request.args.get('_name')
    identities = find_from_identity_collection(search)
    return jsonify(identities), 200

@app.route("/incomes")
def get_incomes():
    schema = IncomeSchema(many=True)
    incomes = schema.dump(
        filter(lambda t: t.type == TransactionType.INCOME, transactions)
    )
    return jsonify(incomes)

@app.route('/incomes', methods=['POST'])
def add_income():
    income = IncomeSchema().load(request.get_json())
    transactions.append(income)
    return "", 204

@app.route('/expenses')
def get_expenses():
    schema = ExpenseSchema(many=True)
    expenses = schema.dump(
        filter(lambda t: t.type == TransactionType.EXPENSE, transactions)
    )
    return jsonify(expenses)

@app.route('/expenses', methods=['POST'])
def add_expense():
    expense = ExpenseSchema().load(request.get_json())
    transactions.append(expense)
    return "", 204

if __name__ == '__main__':
    app.run(debug=True, host="0.0.0.0", port=5000)