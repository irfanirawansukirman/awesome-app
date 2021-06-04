//
//  MovieViewModel.swift
//  BaseApp
//
//  Created by Irfan Irawan Sukirman on 03/06/21.
//

import Foundation
import shared

class MovieViewModel: ObservableObject {
    let movieUseCase = MovieUseCase(apiClient: ApiClient())
        
    @Published var comic = State.loading
        
    init() {
        self.comic = .loading
        movieUseCase.getAllMovies { fetchedComic, error in
            if fetchedComic != nil {
                self.comic = .success(fetchedComic!.results)
            } else {
                self.comic = .error
            }
        }
    }
}
