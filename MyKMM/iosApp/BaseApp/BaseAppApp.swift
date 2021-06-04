//
//  BaseAppApp.swift
//  BaseApp
//
//  Created by Irfan Irawan Sukirman on 03/06/21.
//

import SwiftUI

@main
struct BaseAppApp: App {
    let movieViewModel = MovieViewModel()
    var body: some Scene {
        WindowGroup {
            ContentView(viewModel: movieViewModel)
        }
    }
}
