//
//  State.swift
//  BaseApp
//
//  Created by Irfan Irawan Sukirman on 03/06/21.
//

import Foundation
import shared

enum State {
    case loading
    case success(Array<Result>)
    case error
}
