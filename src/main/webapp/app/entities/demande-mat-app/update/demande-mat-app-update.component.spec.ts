import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { DemandeMatAppService } from '../service/demande-mat-app.service';
import { IDemandeMatApp, DemandeMatApp } from '../demande-mat-app.model';
import { IApprenant } from 'app/entities/apprenant/apprenant.model';
import { ApprenantService } from 'app/entities/apprenant/service/apprenant.service';

import { DemandeMatAppUpdateComponent } from './demande-mat-app-update.component';

describe('DemandeMatApp Management Update Component', () => {
  let comp: DemandeMatAppUpdateComponent;
  let fixture: ComponentFixture<DemandeMatAppUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let demandeMatAppService: DemandeMatAppService;
  let apprenantService: ApprenantService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [DemandeMatAppUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(DemandeMatAppUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DemandeMatAppUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    demandeMatAppService = TestBed.inject(DemandeMatAppService);
    apprenantService = TestBed.inject(ApprenantService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call apprenant query and add missing value', () => {
      const demandeMatApp: IDemandeMatApp = { id: 456 };
      const apprenant: IApprenant = { id: 62440 };
      demandeMatApp.apprenant = apprenant;

      const apprenantCollection: IApprenant[] = [{ id: 64028 }];
      jest.spyOn(apprenantService, 'query').mockReturnValue(of(new HttpResponse({ body: apprenantCollection })));
      const expectedCollection: IApprenant[] = [apprenant, ...apprenantCollection];
      jest.spyOn(apprenantService, 'addApprenantToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ demandeMatApp });
      comp.ngOnInit();

      expect(apprenantService.query).toHaveBeenCalled();
      expect(apprenantService.addApprenantToCollectionIfMissing).toHaveBeenCalledWith(apprenantCollection, apprenant);
      expect(comp.apprenantsCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const demandeMatApp: IDemandeMatApp = { id: 456 };
      const apprenant: IApprenant = { id: 39662 };
      demandeMatApp.apprenant = apprenant;

      activatedRoute.data = of({ demandeMatApp });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(demandeMatApp));
      expect(comp.apprenantsCollection).toContain(apprenant);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<DemandeMatApp>>();
      const demandeMatApp = { id: 123 };
      jest.spyOn(demandeMatAppService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ demandeMatApp });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: demandeMatApp }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(demandeMatAppService.update).toHaveBeenCalledWith(demandeMatApp);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<DemandeMatApp>>();
      const demandeMatApp = new DemandeMatApp();
      jest.spyOn(demandeMatAppService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ demandeMatApp });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: demandeMatApp }));
      saveSubject.complete();

      // THEN
      expect(demandeMatAppService.create).toHaveBeenCalledWith(demandeMatApp);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<DemandeMatApp>>();
      const demandeMatApp = { id: 123 };
      jest.spyOn(demandeMatAppService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ demandeMatApp });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(demandeMatAppService.update).toHaveBeenCalledWith(demandeMatApp);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Tracking relationships identifiers', () => {
    describe('trackApprenantById', () => {
      it('Should return tracked Apprenant primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackApprenantById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });
  });
});
