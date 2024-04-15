
from marshmallow import Schema, fields

class Identity(object):
    def __init__(self, _name, surname):
        self._name = _name
        self.surname = surname

    def __repr__(self):
        return '<Identity(name={self.surname!r})>'.format(self=self)

class IdentitySchema(Schema):
    _name = fields.Str()
    surname = fields.Str()