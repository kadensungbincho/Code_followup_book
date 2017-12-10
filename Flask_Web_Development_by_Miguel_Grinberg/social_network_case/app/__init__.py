from flask import Flask, render_template
from flask_login import LoginManager()
from flask_sqlalchemy import SQLAlchemy

from .auth import auth as auth_blueprint

login_manager = LoginManager()
login_manager.session_protection = 'strong'
login_manager.login_view = 'auth.login' # need prefix cause route is inside a blueprint

db = SQLAlchemy()

def create_app():
    app = Flask(__name__)
    app.register_blueprint(auth_blueprint, url_prefix='/auth')
    db.init_app(app)
    login_manager.init_app(app)

    return app
