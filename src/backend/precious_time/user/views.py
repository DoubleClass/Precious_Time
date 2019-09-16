from django.core.serializers import serialize
from django.shortcuts import render
import json
from django.views.decorators.csrf import csrf_exempt
from django.http.response import HttpResponse
from django.contrib.auth.hashers import make_password, check_password
from user.models import User
NOT_ALLOWED_GET = 'This method does not allowed get'


def register_email(user_id, pw):
    user_query_1 = User.objects.filter(id=user_id)
    user_query_2 = User.objects.filter(email=user_id)
    if len(user_query_1) == 0 and len(user_query_2) == 0:
        user_instance = User(email=user_id, id=user_id, pw=pw)
        user_instance.save()
        return True
    else:
        return False


def register_phone(user_id, pw):
    user_query_1 = User.objects.filter(id=user_id)
    user_query_2 = User.objects.filter(phone=user_id)
    if len(user_query_1) == 0 and len(user_query_2) == 0:
        user_instance = User(phone=user_id, id=user_id, pw=pw)
        user_instance.save()
        return True
    else:
        return False


def register_name(user_id, pw):
    user_query_1 = User.objects.filter(id=user_id)
    user_query_2 = User.objects.filter(name=user_id)
    if len(user_query_1) == 0 and len(user_query_2) == 0:
        user_instance = User(name=user_id, id=user_id, pw=pw)
        user_instance.save()
        return True
    else:
        return False


@csrf_exempt
def register(request):
    code = 1
    if request.method == 'POST':
        in_data = json.loads(request.body, strict=False)
        value_type = in_data["code"]
        user_id = in_data["id"]
        pw = in_data["pw"]
        pw = make_password(pw)
        if value_type == '1':
            # 使用邮箱注册
            if not register_email(user_id, pw):
                code = 2
        elif value_type == '2':
            # 使用手机号注册
            if not register_phone(user_id, pw):
                code = 2
        else:
            # 使用用户名注册
            if not register_name(user_id, pw):
                code = 2
        out_data = {"code": code}
        return HttpResponse(json.dumps(out_data))
    else:
        return HttpResponse(NOT_ALLOWED_GET)


def login_email(user_id, pw):
    code = 1
    user_query = User.objects.filter(email=user_id)
    if len(user_query) == 0:
        code = 2
    else:
        real_pw = user_query[0].pw
        if not check_password(pw, real_pw):
            code = 3
    return code


def login_phone(user_id, pw):
    code = 1
    user_query = User.objects.filter(phone=user_id)
    if len(user_query) == 0:
        code = 2
    else:
        real_pw = user_query[0].pw
        if not check_password(pw, real_pw):
            code = 3
    return code


def login_name(user_id, pw):
    code = 1
    user_query = User.objects.filter(name=user_id)
    if len(user_query) == 0:
        code = 2
    else:
        real_pw = user_query[0].pw
        if not check_password(pw, real_pw):
            code = 3
    return code


def sync_data():
    print("to be done")


@csrf_exempt
def login(request):
    if request.method == 'POST':
        code = 1
        in_data = json.loads(request.body, strict=False)
        value_type = in_data["code"]
        user_id = in_data["id"]
        pw = in_data["pw"]
        if value_type == '1':
            code = login_email(user_id, pw)
        elif value_type == '2':
            code = login_phone(user_id, pw)
        else:
            code = login_name(user_id, pw)
        if code == 1:
            sync_data()
        out_data = {"code": code}
        return HttpResponse(json.dumps(out_data))
    else:
        return HttpResponse(NOT_ALLOWED_GET)


