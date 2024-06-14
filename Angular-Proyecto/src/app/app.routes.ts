import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: '',
        loadComponent: () => import('./inicio/inicio.component').then(m => m.InicioComponent)
    },
    {
        path: 'agregar',
        loadComponent: () => import('./agregar/agregar.component').then(m => m.AgregarComponent)
    },
    {
        path: 'listar',
        loadComponent: () => import('./listar/listar.component').then(m => m.ListaComponent)
    },
    {
        path: 'editar/:id',
        loadComponent: () => import('./editar/editar.component').then(m => m.EditarComponent)
    }
];