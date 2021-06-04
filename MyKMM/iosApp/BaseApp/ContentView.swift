//
//  ContentView.swift
//  BaseApp
//
//  Created by Irfan Irawan Sukirman on 03/06/21.
//

import SwiftUI
import shared

struct ContentView: View {
    @ObservedObject private(set) var viewModel: MovieViewModel
        
        var body: some View {
            comicView()
        }
        
        private func comicView() -> some View {
            switch viewModel.comic {
            case .loading:
                return AnyView(Text("Loading"))
            case .success(let results):
                return AnyView(VStack {
                    RemoteImage(url: "https://image.tmdb.org/t/p/w500/" + results[4].posterPath)
                    Text(results[4].originalTitle)
                })
            case .error:
                return AnyView(Text("Error"))
            }
        }
}
