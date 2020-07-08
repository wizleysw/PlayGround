from rest_framework.response import Response
from rest_framework.permissions import AllowAny
from rest_framework.status import HTTP_400_BAD_REQUEST, HTTP_200_OK, HTTP_201_CREATED
from rest_framework.views import APIView

from api.serializer import StudentSerializer
from api.models import Student

"""
GET : /api/student/<str:name>
용도 : 학생의 정보를 가져옴

POST : /api/student
용도 : 학생의 정보를 추가함

DELETE : /api/student/delete/<str:name>
용도 : 학생의 정보를 삭제함
"""
class StudentAPI(APIView):
    permission_classes = (AllowAny,)

    def get(self, request, name):
        try:
            student = Student.objects.get(name=name)
            serialized = StudentSerializer(student)
            return Response(serialized.data, status=HTTP_200_OK)
        except:
            return Response(status=HTTP_400_BAD_REQUEST)

    def post(self, request):

        try:
            name = request.data['name']
            age = request.data['age']

            student = Student(name=name, age=age)
            student.save()
            return Response(status=HTTP_201_CREATED)
        except:
            return Response(status=HTTP_400_BAD_REQUEST)

    def delete(self, request, name):
        try:
            student = Student.objects.get(name=name)
            student.delete()
            return Response(status=HTTP_200_OK)
        except:
            return Response(status=HTTP_400_BAD_REQUEST)