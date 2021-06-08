# -*- coding: utf-8 -*-
"""
Created on Fri Apr  2 13:35:48 2021

@author: hugo
"""

# =============================================================================
# RAFA HABA DIAZ 79030459K
# HUGO PUERTO ROSELLO 76884689J
# =============================================================================ruta1=generateRoute(ciudades)


import random
import math
from matplotlib import pyplot as plt
import numpy as np
#import tsp


# https://gist.github.com/davidlainesv/b3786d90994d21195f7d


file='berlin52.tsp' #el mejor resultado es 7542

with open(file) as fp:
    ciudades=[]
    cont=1
    for line in iter(fp.readline, "EOF\n"):
        row = line.split()
        if row[0].isnumeric():
            ciudades.append({'id':cont,'x':int(float(row[1])),'y':int(float(row[2]))})
            cont+=1


# DISTANCIA ENTRE DOS CIUDADES, DISTANCIA EUCLIDEA

def distancia(city1,city2):
    x1=city1['x']
    x2=city2['x']
    
    y1=city1['y']
    y2=city2['y']
    
    dist= math.sqrt((x1-x2)**2+(y1-y2)**2)
    
    return dist


# FITNESS DE UNA RUTA

def fitness(ruta):
    fitness_ruta=0.0
    for i in range(len(ruta)):
        if (i<len(ruta)-1):
            fitness_ruta+=distancia(ruta[i],ruta[i+1])
        else:
            fitness_ruta+=distancia(ruta[i],ruta[0])
    
    return fitness_ruta



# =============================================================================
# GENERACION DE LA POBLACION
# =============================================================================
            
def pertenece(x,lista):
    centinela=False
    i=0
    while(not(centinela) and i<len(lista)):
        if (x==lista[i]):
            centinela=True
        i+=1
    return centinela
        
origen=random.randint(0,len(ciudades)-1)

def generateRoute(ciudades):
    long=len(ciudades)-1
    
    ruta=[]
    lista=[]
        
    lista.append(origen)
    ruta.append(ciudades[origen])
    
    for i in range(long):
       
        next_city=random.randint(0,long)
        
        cent=pertenece(next_city,lista)
     
        while(cent):
                  
            next_city=random.randint(0,long)
            cent=pertenece(next_city,lista)
            
        lista.append(next_city)
        ruta.append(ciudades[next_city])
            
    ruta.append(ciudades[origen])
    
    return ruta


def generatePoblacion(ciudades,pop_size):
    pop=[]
    for i in range(pop_size):
        pop.append(generateRoute(ciudades))
        
    return pop
    

def sortByFitness(poblacion):
    
    poblacion=sorted(poblacion,key=fitness)
    
    return poblacion


# =============================================================================
# PINTA LOS NODOS Y LOS CAMINOS ENTRE CADA NODO
# =============================================================================ruta1=generateRoute(ciudades)

def pintaRuta(ruta):
    coordx=[]
    coordy=[]

    i=0
    for city in ruta: 
        if (i==0): # ESTO LO QUIERO PARA SABER CUAL ES EL ORIGEN Y DIFERENCIARLO
            origenx=city['x']
            origeny=city['y']
           
        coordx.append(city['x'])
        coordy.append(city['y'])

        i+=1

    for i in range(len(coordx)-1):
        a=coordx[i]
        b=coordy[i]
        c=coordx[i+1]
        d=coordy[i+1]
        if (a==c):
            x=np.ones(10)*a
            y=np.linspace(b,d,10)
            plt.plot(x,y,'k')
        else:
            x=np.linspace(a,c)
            y=b+(x-a)*(d-b)/(c-a)
            plt.plot(x,y,'k')

        plt.plot(coordx,coordy,'ro')
        plt.plot(origenx,origeny,'ko')
    plt.show


# =============================================================================
#  GENERACION DE UN NUEVO INDIVIDUO
# Es una mezcla homogenea de ambos caminos, por ejemplo, si los genes del
# padre fuesen [a1,b1,c1,d1] y los de la madre [a2,b2,c2,d2], los del hijo 
# serian [a1,b2,c1,d2]
# =============================================================================

# def crossover(padre,madre):
#     num_genes_padre=int(len(padre)/2)
#     num_genes_madre=len(padre)-num_genes_padre
    
#     hijo=padre.copy()
#     lista_ciudades=[]
    
#     for i in range(1,num_genes_padre): # Asi no incluimos al origen ahora, y podremos meterlo despues en el siguiente bucle for
#             lista_ciudades.append(padre[i]['id'])
#     # Este bucle nos sirve para saber que ciudades estan ya metidas y asi no repetirlas
    
#     # En el siguiente bucle, metemos los genes de la madre, pero solamente si esa ciudad no esta, sino cogemos esa posicion para meterle luego mas tarde las ciudades que falten por meter
#     huecos=[]
#     for i in range(num_genes_padre,len(padre)):
#         id_city=madre[i]['id']
#         if (not(pertenece(id_city,lista_ciudades))):
#             lista_ciudades.append(id_city)
#             hijo[i]=madre[i]
#         else:
#             huecos.append(i) # Aqui meto la posicion que no ha variado y la tendre que cambiar despues
        
#     cities_not_included=[]
#     for i in range(len(ciudades)):
#         id_city=ciudades[i]['id']
#         if (not(pertenece(id_city,lista_ciudades))):
#             cities_not_included.append(ciudades[i])
            
#     for i in range(len(cities_not_included)):
#         j=huecos[i]
#         hijo[j]=cities_not_included[i]
                
#     return hijo



def crossover(padre,madre):
    num_genes_padre=random.randint(1,len(padre)-2)
    num_genes_madre=len(padre)-num_genes_padre
    
    hijo=[]
    hijo.append(padre[0]) # Metemos el origen
    lista_ciudades=[]
    lista_ciudades.append(padre[0]['id'])
    
    start=random.randint(1,len(padre)-num_genes_padre-1)
    
    for i in range(num_genes_padre): 
            lista_ciudades.append(padre[start+i]['id'])
            hijo.append(padre[start+i])
    
    for i in range(len(madre)):
        id_city=madre[i]['id']
        if (not(pertenece(id_city,lista_ciudades))):
            lista_ciudades.append(id_city)
            hijo.append(madre[i])

    hijo.append(padre[len(padre)-1]) # Le añadimos el destino (=origen)
    return hijo

    
# def crossover(parent1, parent2):
#     child = []
    
#     childP1 = []
#     childP2 = []
    
#     geneA = int(random.random() * len(parent1))
#     geneB = int(random.random() * len(parent1))
    
#     startGene = min(geneA, geneB)
#     endGene = max(geneA, geneB)

#     for i in range(startGene, endGene):
#         childP1.append(parent1[i])
        
#     childP2 = [item for item in parent2 if item not in childP1]

#     child = childP1 + childP2

#     return child
    
    
    
    
    
def distanciaEntreCiudades(ruta):
    lista=[]
    for i in range(len(ruta)-1):
        lista.append((i+1,distancia(ruta[i],ruta[i+1])))
    return lista


def ordenaPorDistancia(ruta):
 
    ruta_distancia=distanciaEntreCiudades(ruta)
 
    ruta_distancia.sort(key = lambda x: x[1])  

    return ruta_distancia


# def mutate(ruta, prob_mutacion):
#     r=random.random()
#     n=10
#     if (r<prob_mutacion):
#         city1=1
#         city2=1
#         r2=random.random()
#         if (r<0.5):
#             rutaOrd=ordenaPorDistancia(ruta)
#             rutaOrd.reverse()
#             pos1=1
#             pos2=1
#             cent=False
#             while(pos1==pos2 or cent):
#                 pos1=random.randint(1,n)
#                 pos2=random.randint(1,n)  
                
#                 city1=rutaOrd[pos1][0]
#                 city2=rutaOrd[pos2][0]
                
#                 if (city1==origen or city2==origen):
#                     cent=True
#                 else:
#                     cent=False
            
#         else:
#             city1=1
#             city2=1
#             while(city1==city2):
#                 city1=random.randint(1, len(ciudades)-2)
#                 city2=random.randint(1, len(ciudades)-2)    
            
#         ruta1=ruta[city1]
#         ruta2=ruta[city2]
        
#         ruta[city1]=ruta2
#         ruta[city2]=ruta1
          
#     return ruta  
            
def mutate(ruta, prob_mutacion):
    r=random.random()
    if (r<prob_mutacion):                     
        city1=1
        city2=1
        while(city1==city2):
            city1=random.randint(1, len(ciudades)-2) # Tenemos que quitar el primero y el ultimo, que se corresponden con el origen
            city2=random.randint(1, len(ciudades)-2)    
            
        ruta1=ruta[city1]
        ruta2=ruta[city2]
        
        ruta[city1]=ruta2
        ruta[city2]=ruta1
        
    return ruta
        
        

def select(sorted_population):
    n=len(sorted_population)
    i=1
    j=1
    while(i==j):
        i=random.randint(0,n-1)
        j=random.randint(0,n-1)
    return (sorted_population[i],sorted_population[j])        


        
def siguiente_generacion(previous_population):
    sorted_by_fitness_population = sortByFitness(previous_population)
    population_size = len(previous_population)

    for i in range(population_size):
        (father,mother)=select(sorted_by_fitness_population)
        individual = crossover(father, mother)
        
        individual = mutate(individual,prob_mutacion=0.2)
  
        previous_population.append(individual) 
        
    previous_population=sortByFitness(previous_population)

    tam=len(previous_population)

    new_generation=[]
    
    for i in range(population_size):
        new_generation.append(previous_population[i])

    return new_generation
        
        
        
        
        
# =============================================================================
# MAIN
# =============================================================================


def convergencia(lista,num):
    lista1=lista.copy()
    eps=1e-4
    conv=True
    if (len(lista1)<=int(num)):
        conv=False
    else:
        for i in range(0,len(lista1)-num): # Pa quedarme con los n ulitmos elementos de la lista
            lista1.pop(0)
        conv=(abs(max(lista1)-min(lista1))<eps) # Que compruebe si todos los valores de la lista son iguales
    
    return conv
    
#generations = 1500
number_of_routes=500

population = generatePoblacion(ciudades,pop_size=number_of_routes)

i = 1
bestFitness = []

while (True):
    if (i==1):
        print('Generacion '+str(i)+'-esima:')

        best_individual = sortByFitness(population)[-1]
        
        print('Fitness del mejor individuo: '+str(fitness(best_individual)))
        
        #convergencia=fitness(best_individual)

        pintaRuta(best_individual)
        plt.show()       
    
    if (i%100==0):
        print('Generacion '+str(i)+'-esima:')

        best_individual = sortByFitness(population)[-1]
        
        print('Fitness del mejor individuo: '+str(fitness(best_individual)))
        
        pintaRuta(best_individual)
        plt.show()

    conv=convergencia(bestFitness,300)
    if (conv):
        break

    i += 1

    population = siguiente_generacion(population)
    best_individual = sortByFitness(population)[-1]
    bestFitness.append(fitness(best_individual))
    
pintaRuta(best_individual)
plt.show()
    
best_individual = sortByFitness(population)[-1]
plt.plot(bestFitness)
plt.show()
print("\nFINAL RESULT")
print(best_individual, fitness(best_individual))





