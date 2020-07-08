import graphene
from api.models import Student
from graphene_django import DjangoObjectType


class StudentType(DjangoObjectType):
    class Meta:
        model = Student


class Query(graphene.ObjectType):
    students = graphene.List(StudentType,
                             name=graphene.String())

    def resolve_students(self, info, name):
        return Student.objects.filter(name=name)


class AddStudent(graphene.Mutation):
    success = graphene.Boolean()

    class Arguments:
        name = graphene.String(required=True)
        age = graphene.Int(required=True)

    def mutate(self, info, name, age):
        try:
            student = Student(name=name, age=age)
            student.save()
            return AddStudent(success=True)
        except:
            return AddStudent(success=False)


class DeleteStudent(graphene.Mutation):
    success = graphene.Boolean()

    class Arguments:
        name = graphene.String(required=True)

    def mutate(self, info, name):
        try:
            student = Student.objects.get(name=name)
            student.delete()
            return DeleteStudent(success=True)
        except:
            return DeleteStudent(success=False)


class Mutation(graphene.ObjectType):
    addStudent = AddStudent.Field()
    deleteStudent = DeleteStudent.Field()

schema = graphene.Schema(
    query=Query,
    mutation=Mutation)
