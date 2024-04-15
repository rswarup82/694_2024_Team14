import datetime as dt

from marshmallow import Schema, fields

class User(object):
    def __init__(self, _id, name, screen_name, location, description, verified, followers_count, friends_count, listed_count, favorites_count, statuses_count, created_at, lang):
        self._id = _id
        self.name = name
        self.screen_name = screen_name
        self.location = location
        self.description = description
        self.verified = verified
        self.followers_count = followers_count
        self.friends_count = friends_count
        self.listed_count = listed_count
        self.favorites_count = favorites_count
        self.statuses_count = statuses_count
        self.created_at = created_at
        self.lang = lang

    def __repr__(self):
        return '<User(name={self.name!r})>'.format(self=self)

class UserSchema(Schema):
    _id = fields.Number()
    name = fields.Str()
    screen_name = fields.Str()
    location = fields.Str()
    description = fields.Str()
    verified = fields.Boolean()
    followers_count = fields.Number()
    friends_count = fields.Number()
    listed_count = fields.Number()
    favorites_count = fields.Number()
    statuses_count = fields.Number()
    created_at = fields.DateTime()
    lang = fields.Str()