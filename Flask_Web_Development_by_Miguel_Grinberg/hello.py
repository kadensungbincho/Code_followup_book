import os

from flask_script import Manager
from flask import Flask, render_template
app = Flask(__name__)
manager = Manager(app)

app.config['MAIL_SERVER'] = 'kadensungbincho@gmail.com'
app.config['MAIL_PORT'] = 587
app.config['MAIL_USE_TLS'] = True
app.config['MAIL_USERNAME'] = os.environ.get('MAIL_USERNAME')
app.config['MAIL_PASSWORD'] = os.environ.get('MAIL_PASSWORD')

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/user/<name>')
def user(name):
    return render_template('index.html', name=name)

if __name__ == '__main__':
    manager.run()
    # app.run(debug=True)