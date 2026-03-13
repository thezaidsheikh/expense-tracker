from flask import Flask
from flask import request,jsonify
from service.MessageService import MessageService

app = Flask(__name__)

@app.route('/health', methods=['GET'])
def health():
    return "OK"

@app.route('/v1/ds/message', methods=['POST'])
def handle_message():
    message_service = MessageService()
    message = request.json.get('message')
    res = message_service.handle_message(message)
    if res == None:
        return "Not a bank SMS"
    return res

if __name__ == '__main__':
    app.run(debug=True, host='127.0.0.1', port=3005)
