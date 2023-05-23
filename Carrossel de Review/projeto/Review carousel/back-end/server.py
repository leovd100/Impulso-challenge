from flask import Flask, request
from flask_cors import CORS
import json
import os

app = Flask(__name__)
CORS(app)

@app.route("/")
def home():
    return "server on"


@app.route("/users")
def users():
    files = open_file_and_read()
    list_json = {}

    for index in range(0, len(files)):
        list_json[index] = (json.loads(files[index]))
    return list_json


@app.route("/users", methods=["POST"])
def insert_data():
    data = json.loads(request.data)
    name = data["name"]
    job = data["job"]
    review = data["review"]
    photo = data["photo"]
    create(create_dict(name, job, review, photo))
    return "Ok"


def create(info):
    try:
        last_key = list(info.keys())[-1]

        with open("./data/data.txt", 'a') as file:
            if not check_file_is_empty():
                file.write("{")
            else:
                file.write("{")
            for key, value in info.items():
                if key == last_key:
                    file.writelines(f' \"{key}\" : \"{value}\" ')
                else:
                    file.writelines(f" \"{key}\" : \"{value}\", ")
            file.write("}\n")
            file.close()
    except:
        info = ""
        with open("./data/data.txt", 'w') as file:
            file.close()
        return info


def check_file_is_empty():
    with open('./data/data.txt', 'r') as file:
        file.seek(0, os.SEEK_END)
        isempty = file.tell() == 0
        file.seek(0)  # rebobinar o arquivo
        return isempty


def open_file_and_read():
    try:
        with open("./data/data.txt", 'r') as file:
            return file.read().splitlines()
    except:
        info = ""
        with open("./data/data.txt", 'w') as file:
            file.close()
        return info


def create_dict(name, job, review, photo):
    return {"name": name, "job": job, "review": review, "photo": photo}


if __name__ == "__main__":
    app.run(debug=True)
