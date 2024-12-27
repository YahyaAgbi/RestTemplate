import { Component } from '@angular/core';
import { Client } from '../../models/client';
import { ApiService } from '../../service/api.service';

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css'] // Correction de styleUrls
})
export class ClientComponent {
  clients: Client[] = [];
  newClient: Client = new Client();
  isEditMode = false;
  selectedClientId: number | null = null;
  successMessage: string = '';
  errorMessage: string = '';

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {
    this.loadClients();
  }

  // Charger tous les clients
  loadClients(): void {
    this.apiService.getAllClients().subscribe({
      next: (data) => {
        this.clients = data;
        this.errorMessage = ''; // Réinitialisation des erreurs
      },
      error: (err) => {
        console.error('Erreur lors du chargement des clients :', err);
        this.errorMessage = 'Erreur lors du chargement des clients.';
      }
    });
  }

  // Ajouter ou mettre à jour un client
  addOrUpdateClient(): void {
    if (this.isEditMode && this.selectedClientId !== null) {
      // Mise à jour d'un client existant
      this.apiService.UpdateClient(this.selectedClientId, this.newClient).subscribe({
        next: () => {
          this.setMessage('success', 'Client mis à jour avec succès.');
          this.resetForm();
          this.loadClients();
        },
        error: (err) => {
          console.error('Erreur lors de la mise à jour du client :', err);
          this.setMessage('error', 'Erreur lors de la mise à jour du client.');
        }
      });
    } else {
      // Ajout d'un nouveau client
      this.apiService.saveClient(this.newClient).subscribe({
        next: () => {
          this.setMessage('success', 'Client ajouté avec succès.');
          this.resetForm();
          this.loadClients();
        },
        error: (err) => {
          console.error('Erreur lors de l\'ajout du client :', err);
          this.setMessage('error', 'Erreur lors de l\'ajout du client.');
        }
      });
    }
  }

  // Supprimer un client
  deleteClient(clientId: number): void {
    this.apiService.DeleteClient(clientId).subscribe({
      next: () => {
        this.setMessage('success', 'Client supprimé avec succès.');
        this.loadClients();
      },
      error: (err) => {
        console.error('Erreur lors de la suppression du client :', err);
        this.setMessage('error', 'Erreur lors de la suppression du client.');
      }
    });
  }

  // Activer le mode édition
  editClient(client: Client): void {
    if (!client.id) {
      this.setMessage('error', 'Client invalide pour l\'édition.');
      return;
    }
    this.isEditMode = true;
    this.selectedClientId = client.id;
    this.newClient = { ...client }; // Cloner pour éviter de modifier directement
  }

  // Réinitialiser le formulaire
  resetForm(): void {
    this.isEditMode = false;
    this.selectedClientId = null;
    this.newClient = new Client();
  }

  // Gestion des messages (succès/erreur)
  setMessage(type: 'success' | 'error', message: string): void {
    if (type === 'success') {
      this.successMessage = message;
      setTimeout(() => (this.successMessage = ''), 5000); // Masquer après 5 secondes
    } else {
      this.errorMessage = message;
      setTimeout(() => (this.errorMessage = ''), 5000); // Masquer après 5 secondes
    }
  }
}
