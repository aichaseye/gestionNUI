import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { IDemandeMatApp, DemandeMatApp } from '../demande-mat-app.model';
import { DemandeMatAppService } from '../service/demande-mat-app.service';
import { IApprenant } from 'app/entities/apprenant/apprenant.model';
import { ApprenantService } from 'app/entities/apprenant/service/apprenant.service';
import { Sexe } from 'app/entities/enumerations/sexe.model';

@Component({
  selector: 'jhi-demande-mat-app-update',
  templateUrl: './demande-mat-app-update.component.html',
})
export class DemandeMatAppUpdateComponent implements OnInit {
  isSaving = false;
  sexeValues = Object.keys(Sexe);

  apprenantsCollection: IApprenant[] = [];

  editForm = this.fb.group({
    id: [],
    nomCompletApp: [null, [Validators.required]],
    email: [null, [Validators.required]],
    telephone: [],
    dateNaissance: [null, [Validators.required]],
    adresse: [],
    etatDemande: [],
    sexe: [null, [Validators.required]],
    nationalite: [null, [Validators.required]],
    numeroInscription: [null, [Validators.required]],
    objet: [],
    matriculeApp: [],
    anneeDemande: [null, [Validators.required]],
    apprenant: [],
  });

  constructor(
    protected demandeMatAppService: DemandeMatAppService,
    protected apprenantService: ApprenantService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ demandeMatApp }) => {
      this.updateForm(demandeMatApp);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const demandeMatApp = this.createFromForm();
    if (demandeMatApp.id !== undefined) {
      this.subscribeToSaveResponse(this.demandeMatAppService.update(demandeMatApp));
    } else {
      this.subscribeToSaveResponse(this.demandeMatAppService.create(demandeMatApp));
    }
  }

  trackApprenantById(index: number, item: IApprenant): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDemandeMatApp>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(demandeMatApp: IDemandeMatApp): void {
    this.editForm.patchValue({
      id: demandeMatApp.id,
      nomCompletApp: demandeMatApp.nomCompletApp,
      email: demandeMatApp.email,
      telephone: demandeMatApp.telephone,
      dateNaissance: demandeMatApp.dateNaissance,
      adresse: demandeMatApp.adresse,
      etatDemande: demandeMatApp.etatDemande,
      sexe: demandeMatApp.sexe,
      nationalite: demandeMatApp.nationalite,
      numeroInscription: demandeMatApp.numeroInscription,
      objet: demandeMatApp.objet,
      matriculeApp: demandeMatApp.matriculeApp,
      anneeDemande: demandeMatApp.anneeDemande,
      apprenant: demandeMatApp.apprenant,
    });

    this.apprenantsCollection = this.apprenantService.addApprenantToCollectionIfMissing(this.apprenantsCollection, demandeMatApp.apprenant);
  }

  protected loadRelationshipsOptions(): void {
    this.apprenantService
      .query({ filter: 'demandematapp-is-null' })
      .pipe(map((res: HttpResponse<IApprenant[]>) => res.body ?? []))
      .pipe(
        map((apprenants: IApprenant[]) =>
          this.apprenantService.addApprenantToCollectionIfMissing(apprenants, this.editForm.get('apprenant')!.value)
        )
      )
      .subscribe((apprenants: IApprenant[]) => (this.apprenantsCollection = apprenants));
  }

  protected createFromForm(): IDemandeMatApp {
    return {
      ...new DemandeMatApp(),
      id: this.editForm.get(['id'])!.value,
      nomCompletApp: this.editForm.get(['nomCompletApp'])!.value,
      email: this.editForm.get(['email'])!.value,
      telephone: this.editForm.get(['telephone'])!.value,
      dateNaissance: this.editForm.get(['dateNaissance'])!.value,
      adresse: this.editForm.get(['adresse'])!.value,
      etatDemande: this.editForm.get(['etatDemande'])!.value,
      sexe: this.editForm.get(['sexe'])!.value,
      nationalite: this.editForm.get(['nationalite'])!.value,
      numeroInscription: this.editForm.get(['numeroInscription'])!.value,
      objet: this.editForm.get(['objet'])!.value,
      matriculeApp: this.editForm.get(['matriculeApp'])!.value,
      anneeDemande: this.editForm.get(['anneeDemande'])!.value,
      apprenant: this.editForm.get(['apprenant'])!.value,
    };
  }
}
