import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'localite',
        data: { pageTitle: 'dbGestionMatricule4App.localite.home.title' },
        loadChildren: () => import('./localite/localite.module').then(m => m.LocaliteModule),
      },
      {
        path: 'inspection',
        data: { pageTitle: 'dbGestionMatricule4App.inspection.home.title' },
        loadChildren: () => import('./inspection/inspection.module').then(m => m.InspectionModule),
      },
      {
        path: 'etablissement',
        data: { pageTitle: 'dbGestionMatricule4App.etablissement.home.title' },
        loadChildren: () => import('./etablissement/etablissement.module').then(m => m.EtablissementModule),
      },
      {
        path: 'apprenant',
        data: { pageTitle: 'dbGestionMatricule4App.apprenant.home.title' },
        loadChildren: () => import('./apprenant/apprenant.module').then(m => m.ApprenantModule),
      },
      {
        path: 'inscription',
        data: { pageTitle: 'dbGestionMatricule4App.inscription.home.title' },
        loadChildren: () => import('./inscription/inscription.module').then(m => m.InscriptionModule),
      },
      {
        path: 'personnel',
        data: { pageTitle: 'dbGestionMatricule4App.personnel.home.title' },
        loadChildren: () => import('./personnel/personnel.module').then(m => m.PersonnelModule),
      },
      {
        path: 'classe',
        data: { pageTitle: 'dbGestionMatricule4App.classe.home.title' },
        loadChildren: () => import('./classe/classe.module').then(m => m.ClasseModule),
      },
      {
        path: 'diplome',
        data: { pageTitle: 'dbGestionMatricule4App.diplome.home.title' },
        loadChildren: () => import('./diplome/diplome.module').then(m => m.DiplomeModule),
      },
      {
        path: 'serie-etude',
        data: { pageTitle: 'dbGestionMatricule4App.serieEtude.home.title' },
        loadChildren: () => import('./serie-etude/serie-etude.module').then(m => m.SerieEtudeModule),
      },
      {
        path: 'filiere-etude',
        data: { pageTitle: 'dbGestionMatricule4App.filiereEtude.home.title' },
        loadChildren: () => import('./filiere-etude/filiere-etude.module').then(m => m.FiliereEtudeModule),
      },
      {
        path: 'niveau-etude',
        data: { pageTitle: 'dbGestionMatricule4App.niveauEtude.home.title' },
        loadChildren: () => import('./niveau-etude/niveau-etude.module').then(m => m.NiveauEtudeModule),
      },
      {
        path: 'releve-note',
        data: { pageTitle: 'dbGestionMatricule4App.releveNote.home.title' },
        loadChildren: () => import('./releve-note/releve-note.module').then(m => m.ReleveNoteModule),
      },
      {
        path: 'programme',
        data: { pageTitle: 'dbGestionMatricule4App.programme.home.title' },
        loadChildren: () => import('./programme/programme.module').then(m => m.ProgrammeModule),
      },
      {
        path: 'demande-mat-app',
        data: { pageTitle: 'dbGestionMatricule4App.demandeMatApp.home.title' },
        loadChildren: () => import('./demande-mat-app/demande-mat-app.module').then(m => m.DemandeMatAppModule),
      },
      {
        path: 'demande-mat-etab',
        data: { pageTitle: 'dbGestionMatricule4App.demandeMatEtab.home.title' },
        loadChildren: () => import('./demande-mat-etab/demande-mat-etab.module').then(m => m.DemandeMatEtabModule),
      },
      {
        path: 'matiere',
        data: { pageTitle: 'dbGestionMatricule4App.matiere.home.title' },
        loadChildren: () => import('./matiere/matiere.module').then(m => m.MatiereModule),
      },
      {
        path: 'image',
        data: { pageTitle: 'dbGestionMatricule4App.image.home.title' },
        loadChildren: () => import('./image/image.module').then(m => m.ImageModule),
      },
      {
        path: 'bon',
        data: { pageTitle: 'dbGestionMatricule4App.bon.home.title' },
        loadChildren: () => import('./bon/bon.module').then(m => m.BonModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
