from django.core.serializers import serialize
from django.shortcuts import render
import json
from django.http.response import HttpResponse
from user.models import User
NOT_ALLOWED_GET = 'This method does not allowed get'
from django.views.decorators.csrf import csrf_exempt


@csrf_exempt
def login(request):
    """用户从web端登录"""
    if request.method == 'POST':
        in_data = json.loads(request.body, strict=False)
        name = in_data["name"]
        pw = in_data['pw']
        login_success = False
        my_data = User.objects.filter(name=name).values()[0]
        realpw = my_data['pw']
        if pw == realpw:
            print('i love u')
        else:
            print("fuck")
        print("**")
        print(my_data)
        print(len(my_data))
        if len(my_data) != 0:
            login_success = True
        out_data = {"code": login_success}
        out_data = json.dumps(out_data)
        return HttpResponse(out_data)
    else:
        return HttpResponse(NOT_ALLOWED_GET)
