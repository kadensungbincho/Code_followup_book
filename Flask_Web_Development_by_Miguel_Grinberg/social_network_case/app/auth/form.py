from flask_wtf import Form
from wtforms import StringField, PasswordField, BooleanField, SubmitField
from wtfomrs.validators import DataRequired, Email

class LoginForm(Form):
    
