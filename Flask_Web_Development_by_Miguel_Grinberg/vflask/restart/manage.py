import os
import coverage
from app import create_app, db
from app.models import User, Role, Permission, Post
from flask_script import Manager, Shell, Server
from flask_migrate import Migrate, MigrateCommand


COV = None
if os.environ.get('FLASK_COVERAGE'):
    import coverage
    COV = coverage.coverage(branch=True, include='app/*')
    COV.start()


app = create_app(os.getenv('FLASK_CONFIG') or 'default')
manager = Manager(app)
migrate = Migrate(app, db)
server = Server(host='0.0.0.0', port=8000)


def make_shell_context():
    return dict(app=app, db=db, User=User, Role=Role, 
                Permission=Permission, Post=Post)
manager.add_command('db', MigrateCommand)
manager.add_command("shell", Shell(make_context=make_shell_context))
manager.add_command('runserver', server)


@manager.command
def test():
    "Run the unit tests. """
    if coverage and not os.environ.get('FLASK_COVERAGE'):
        import sys
        os.environ['FLASK_COVERAGE'] = '1'
        os.execvp(sys.executable, [sys.executable] + sys.argv)
    import unittest
    tests = unittest.TestLoader().discover('tests')
    unittest.TextTestRunner(verbosity=2).run(tests)
    if COV:
        COV.stop()
        COV.save()
        print('Coverage Summmary:')
        COV.report()
        basedir = os.path.abspath(os.path.dirname(__file__))
        covdir = os.path.join(basedir, 'tmp/coverage')
        COV.html_report(directory=covdir)
        print('HTML version: file://%s/index.html' % covdir)
        COV.erase()


if __name__ == '__main__':
    manager.run()