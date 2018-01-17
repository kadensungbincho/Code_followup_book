from flask import Blueprint

auth = Blueprint('autj', __name__)

from . import views
