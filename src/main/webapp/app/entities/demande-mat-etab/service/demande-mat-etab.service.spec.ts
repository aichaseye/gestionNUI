import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import dayjs from 'dayjs/esm';

import { DATE_FORMAT } from 'app/config/input.constants';
import { NomDep } from 'app/entities/enumerations/nom-dep.model';
import { TypeEtab } from 'app/entities/enumerations/type-etab.model';
import { StatutEtab } from 'app/entities/enumerations/statut-etab.model';
import { TypeInspection } from 'app/entities/enumerations/type-inspection.model';
import { IDemandeMatEtab, DemandeMatEtab } from '../demande-mat-etab.model';

import { DemandeMatEtabService } from './demande-mat-etab.service';

describe('DemandeMatEtab Service', () => {
  let service: DemandeMatEtabService;
  let httpMock: HttpTestingController;
  let elemDefault: IDemandeMatEtab;
  let expectedResult: IDemandeMatEtab | IDemandeMatEtab[] | boolean | null;
  let currentDate: dayjs.Dayjs;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(DemandeMatEtabService);
    httpMock = TestBed.inject(HttpTestingController);
    currentDate = dayjs();

    elemDefault = {
      id: 0,
      nomComplet: 'AAAAAAA',
      responsabilite: 'AAAAAAA',
      nomEtab: 'AAAAAAA',
      departement: NomDep.Dakar,
      typeEtab: TypeEtab.LyceeTechnique,
      statut: StatutEtab.Prive,
      anneeDemande: currentDate,
      typeInsp: TypeInspection.IA,
      etatDemande: 'AAAAAAA',
      email: 'AAAAAAA',
      objet: 'AAAAAAA',
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign(
        {
          anneeDemande: currentDate.format(DATE_FORMAT),
        },
        elemDefault
      );

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a DemandeMatEtab', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
          anneeDemande: currentDate.format(DATE_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          anneeDemande: currentDate,
        },
        returnedFromService
      );

      service.create(new DemandeMatEtab()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a DemandeMatEtab', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          nomComplet: 'BBBBBB',
          responsabilite: 'BBBBBB',
          nomEtab: 'BBBBBB',
          departement: 'BBBBBB',
          typeEtab: 'BBBBBB',
          statut: 'BBBBBB',
          anneeDemande: currentDate.format(DATE_FORMAT),
          typeInsp: 'BBBBBB',
          etatDemande: 'BBBBBB',
          email: 'BBBBBB',
          objet: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          anneeDemande: currentDate,
        },
        returnedFromService
      );

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a DemandeMatEtab', () => {
      const patchObject = Object.assign(
        {
          nomEtab: 'BBBBBB',
          statut: 'BBBBBB',
          anneeDemande: currentDate.format(DATE_FORMAT),
          etatDemande: 'BBBBBB',
          objet: 'BBBBBB',
        },
        new DemandeMatEtab()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign(
        {
          anneeDemande: currentDate,
        },
        returnedFromService
      );

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of DemandeMatEtab', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          nomComplet: 'BBBBBB',
          responsabilite: 'BBBBBB',
          nomEtab: 'BBBBBB',
          departement: 'BBBBBB',
          typeEtab: 'BBBBBB',
          statut: 'BBBBBB',
          anneeDemande: currentDate.format(DATE_FORMAT),
          typeInsp: 'BBBBBB',
          etatDemande: 'BBBBBB',
          email: 'BBBBBB',
          objet: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          anneeDemande: currentDate,
        },
        returnedFromService
      );

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a DemandeMatEtab', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addDemandeMatEtabToCollectionIfMissing', () => {
      it('should add a DemandeMatEtab to an empty array', () => {
        const demandeMatEtab: IDemandeMatEtab = { id: 123 };
        expectedResult = service.addDemandeMatEtabToCollectionIfMissing([], demandeMatEtab);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(demandeMatEtab);
      });

      it('should not add a DemandeMatEtab to an array that contains it', () => {
        const demandeMatEtab: IDemandeMatEtab = { id: 123 };
        const demandeMatEtabCollection: IDemandeMatEtab[] = [
          {
            ...demandeMatEtab,
          },
          { id: 456 },
        ];
        expectedResult = service.addDemandeMatEtabToCollectionIfMissing(demandeMatEtabCollection, demandeMatEtab);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a DemandeMatEtab to an array that doesn't contain it", () => {
        const demandeMatEtab: IDemandeMatEtab = { id: 123 };
        const demandeMatEtabCollection: IDemandeMatEtab[] = [{ id: 456 }];
        expectedResult = service.addDemandeMatEtabToCollectionIfMissing(demandeMatEtabCollection, demandeMatEtab);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(demandeMatEtab);
      });

      it('should add only unique DemandeMatEtab to an array', () => {
        const demandeMatEtabArray: IDemandeMatEtab[] = [{ id: 123 }, { id: 456 }, { id: 84219 }];
        const demandeMatEtabCollection: IDemandeMatEtab[] = [{ id: 123 }];
        expectedResult = service.addDemandeMatEtabToCollectionIfMissing(demandeMatEtabCollection, ...demandeMatEtabArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const demandeMatEtab: IDemandeMatEtab = { id: 123 };
        const demandeMatEtab2: IDemandeMatEtab = { id: 456 };
        expectedResult = service.addDemandeMatEtabToCollectionIfMissing([], demandeMatEtab, demandeMatEtab2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(demandeMatEtab);
        expect(expectedResult).toContain(demandeMatEtab2);
      });

      it('should accept null and undefined values', () => {
        const demandeMatEtab: IDemandeMatEtab = { id: 123 };
        expectedResult = service.addDemandeMatEtabToCollectionIfMissing([], null, demandeMatEtab, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(demandeMatEtab);
      });

      it('should return initial array if no DemandeMatEtab is added', () => {
        const demandeMatEtabCollection: IDemandeMatEtab[] = [{ id: 123 }];
        expectedResult = service.addDemandeMatEtabToCollectionIfMissing(demandeMatEtabCollection, undefined, null);
        expect(expectedResult).toEqual(demandeMatEtabCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
